var student = new Vue({
    el : "#student",
    data : {
        studentInfo : {
            id : "",
            username : "",
            name : "",
            department_name : "",
            major_name : ""
        },
        pageSwitchControl : {
            examListDiv : false,
            examOfDoneDiv : false
        },
        examListQueryParams : {
            department_id : "",
            major_id : "",
            teacher_id : "",
            name : ""
        },
        examList : {
            id : "",
            name : "",
            lesson_name : "",
            teacher_name : "",
            department_name : "",
            major_name : "",
            time : "",
            duration : ""
        },
        examDoPageControl : false,
        choiceQuestions : {
            examInfoId : "",
            id : "",
            description : "",
            optionA : "",
            optionB : "",
            optionC : "",
            optionD : "",
            score : "",
            answer : ""
        },
        objectiveQuestions : {
            examInfoId : "",
            id : "",
            description : "",
            score : "",
            answer : ""
        },
        maxScore : 0,
        examInfoOfEdited : {
            id : "",
            name : "",
            lesson_name : "",
            teacher_name : "",
            department_name : "",
            major_name : "",
            time : "",
            duration : "",
            scoreOfGet : ""
        },
        answers : {
            user_id : "",
            exam_id : "",
            choiceAnswers : [],
            objectiveAnswers : []
        },
        interval : "",
        scoresOfGet : {
            exam_name : "",
            lesson_name : "",
            teacher_name : "",
            score_max : "",
            score_choice : "",
            score_objective : "",
            updated_time : ""
        }
    },
    mounted : function () {
        this.getStudentInfo();
        this.pageSwitchControl.examListDiv = true;
    },
    watch : {
        'pageSwitchControl.examListDiv' : {handler : function () {
            if(this.pageSwitchControl.examListDiv) {
                this.getExamList();
            }
        }, deep: true},
        'pageSwitchControl.examOfDoneDiv' : {handler : function () {
            if (this.pageSwitchControl.examOfDoneDiv) {
                this.getScores();
            }
        }, deep: true},
        'examDoPageControl' : {handler : function () {
            if (this.examDoPageControl) {
                this.interval = window.setInterval(this.autoSubmit, 60000);
            }else {
                window.clearInterval(this.interval);
            }
        }}
    },
    methods : {
        switchPage : function (page) {
            for(var key in this.pageSwitchControl){
                this.pageSwitchControl[key] = false;
            }
            switch (page){
                case "examListDiv" : this.pageSwitchControl.examListDiv = true; break;
                case "examOfDoneDiv" : this.pageSwitchControl.examOfDoneDiv = true; break;
            }
        },
        getStudentInfo : function () {
            var _this = this;
            var username = header.getCookieValue("username");
            axios.get("/service/student/info/"+username).then(function (response) {
                if(response.data.status.code === 200) {
                    _this.studentInfo = response.data.data;
                }else {
                    header.toWelcomePage();
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络异常");
            });
        },
        getExamList : function () {
            var _this = this;
            var params = header.requestDataParserOfGet(_this.examQueryParams);
            axios.get("/service/student/exams" + params).then(function (response) {
                if(response.data.status.code === 200){
                    _this.examList = response.data.data;
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }
                else {
                    alert("考试查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        startExam : function (examInfo) {
            var now = new Date().getTime();
            var startTime = new Date(examInfo.time);
            var offSetMill = startTime.getTimezoneOffset() * 60000;
            startTime = startTime.getTime() + offSetMill;
            if(now < startTime){
                alert("考试尚未开始");
                return;
            }
            var endTime = examInfo.time+ examInfo.duration * 60000;
            if(now > endTime){
                alert("考试已经结束");
                return;
            }
            var _this = this;
            axios.get("/service/student/exam/do/getQuestionList/" + examInfo.id).then(function (response) {
                if(response.data.status.code === 200){
                    _this.examInfoOfEdited = examInfo;
                    _this.examDoPageControl = true;
                    _this.choiceQuestions = response.data.data.choiceQuestions;
                    _this.objectiveQuestions = response.data.data.objectiveQuestions;
                    _this.maxScore = 0;
                    for(var i=0; i < _this.choiceQuestions.length; i++){
                        _this.maxScore += _this.choiceQuestions[i].score;
                    }
                    for(var j=0; j < _this.objectiveQuestions.length; j++){
                        _this.maxScore += _this.objectiveQuestions[j].score;
                    }
                    _this.getAnswer();
                }else if(response.data.status.code === 777){
                    alert("考试时间不正确");
                }
                else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }
                else {
                    alert("考试查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        submitAnswer : function () {
            this.answers.exam_id = this.examInfoOfEdited.id;
            this.answers.user_id = this.studentInfo.id;
            this.answers.choiceAnswers = [];
            this.answers.objectiveAnswers = [];
            for(var i=0; i<this.choiceQuestions.length; i++){
                this.answers.choiceAnswers.push('{"id" : "' + this.choiceQuestions[i].id + '", "answer" : "' + this.choiceQuestions[i].answer + '"}');
            }
            for(var j=0; j<this.objectiveQuestions.length; j++){
                this.answers.objectiveAnswers.push('{"id" : "' + this.objectiveQuestions[j].id + '", "answer" : "' + Base.encode(this.objectiveQuestions[j].answer) + '"}');
            }
            axios.post("/service/student/exam/answer", this.answers);
        },
        autoSubmit : function () {
            if (this.examDoPageControl){
                var startTime = new Date(this.examInfoOfEdited.time);
                var offSerMill = startTime.getTimezoneOffset() * 60000;
                startTime = startTime.getTime() + offSerMill;
                var endTime = startTime + this.examInfoOfEdited.duration * 60000;
                var now = new Date().getTime();
                if(now >= startTime && now <= endTime){
                    this.submitAnswer();
                }else {
                    this.examDoPageControl = false;
                }
            }
        },
        getAnswer : function () {
            var _this = this;
            axios.get("/service/student/exam/" + _this.examInfoOfEdited.id + "/" + _this.studentInfo.id).then(function (response) {
                if(response.data.status.code === 200){
                    if(response.data.data.length > 0) {
                        _this.answers = response.data.data[0];
                        _this.setObjectiveAnswer(_this.answers);
                        _this.setChoiceAnswer(_this.answers);
                    }
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }else {
                    alert("历史答案查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        getAnswerById : function (id, answerArray) {
            for (var i=0; i<answerArray.length; i++){
                var json = JSON.parse(answerArray[i]);
                if(json.id === id + ""){
                    return json.answer;
                }
            }
            return "";
        },
        setObjectiveAnswer : function (answer) {
            answer = JSON.parse(answer.answer);
            var objectives = answer.objectiveAnswers;
            for(var i=0; i<this.objectiveQuestions.length; i++){
                this.objectiveQuestions[i].answer = Base.decode(this.getAnswerById(this.objectiveQuestions[i].id, objectives));
            }

        },
        setChoiceAnswer : function (answer) {
            answer = JSON.parse(answer.answer);
            var choices = answer.choiceAnswers;
            for(var j=0; j<this.choiceQuestions.length; j++){
                this.choiceQuestions[j].answer = this.getAnswerById(this.choiceQuestions[j].id, choices);
            }
            var choiceDivs = document.getElementById("choiceQuestionList").children;
            for(var i=0; i<this.choiceQuestions.length; i++){
                if(this.choiceQuestions[i].answer !== ""){
                    var answerOptionIndex = this.choiceQuestions[i].answer.charCodeAt() - 'A'.charCodeAt() + 1;
                    choiceDivs[i].children[answerOptionIndex].style.background = "#29dc29";
                }
            }
        },
        exitExam : function () {
            this.submitAnswer();
            this.examDoPageControl = false;
        },
        chooseThisOption : function (e, question, option) {
            question.answer = option;
            var element = e.currentTarget;
            var brothers = element.parentNode.children;
            for (var i=1; i<brothers.length; i++){
                brothers[i].style.background = "white";
            }
            element.style.background = "#29dc29";
        },
        getScores : function () {
            var _this = this;
            axios.get("/service/student/score/" + this.studentInfo.id).then(function (response) {
                if(response.data.status.code === 200){
                    _this.scoresOfGet = response.data.data;
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }else {
                    alert("查询分数失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        adjustTime : function (date) {
            var time = new Date(date);
            var offSetMill = time.getTimezoneOffset() * 60000;
            time = time.getTime() + offSetMill;
            return new Date(time).toLocaleDateString() + " " + new Date(time).toLocaleTimeString();
        }
    }
});