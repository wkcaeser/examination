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
            duration : ""
        },
        answers : {
            user_id : "",
            exam_id : "",
            choiceAnswers : [],
            objectiveAnswers : []
        }
    },
    mounted : function () {
        this.getStudentInfo();
        this.pageSwitchControl.examListDiv = true;
    },
    watch : {
        'pageSwitchControl.examListDiv' : {handler : function () {
            this.getExamList();
        }, deep: true}
    },
    methods : {
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
            startTime = new Date(startTime.getTime() + offSetMill);
            if(now < startTime){
                alert("考试尚未开始");
                return;
            }
            var endTime = examInfo.time+ examInfo.duration * 6000;
            if(now > endTime){
                alert("考试已经结束");
                return;
            }
            var _this = this;
            axios.get("/service/student/exam/do/getQuestionList/" + examInfo.id).then(function (response) {
                if(response.data.status.code === 200){
                    _this.examDoPageControl = true;
                    _this.choiceQuestions = response.data.data.choiceQuestions;
                    _this.objectiveQuestions = response.data.data.objectiveQuestions;
                    _this.maxScore = 0;
                    _this.examInfoOfEdited = examInfo;
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
                this.answers.objectiveAnswers.push('{"id" : "' + this.objectiveQuestions[j].id + '", "answer" : "' + this.objectiveQuestions[j].answer + '"}');
            }
            axios.post("/service/student/exam/answer", this.answers);
        },
        autoSubmit : function () {
            
        },
        getAnswer : function () {
            var _this = this;
            axios.get("/service/student/exam/" + _this.examInfoOfEdited.id + "/" + _this.studentInfo.id).then(function (response) {
                if(response.data.status.code === 200){
                    if(response.data.data.length > 0) {
                        _this.answers = response.data.data[0];
                        _this.setAnswer(_this.answers);
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
        setAnswer : function (answer) {
            answer = JSON.parse(answer.answer);
            var choices = answer.choiceAnswers;
            var objectives = answer.objectiveAnswers;
            for(var i=0; i<this.objectiveQuestions.length; i++){
                this.objectiveQuestions[i].answer = this.getAnswerById(this.objectiveQuestions[i].id, objectives);
            }
            for(var j=0; j<this.choiceQuestions.length; j++){
                this.choiceQuestions[j].answer = this.getAnswerById(this.choiceQuestions[j].id, choices);
            }
        },
        exitExam : function () {
            this.submitAnswer();
            this.examDoPageControl = false;
        },
        chooseThisOption : function (e, qustion, option) {
            qustion.answer = option;
            var element = e.currentTarget;
            var brothers = element.parentNode.children;
            for (var i=1; i<brothers.length; i++){
                brothers[i].style.background = "white";
            }
            element.style.background = "#29dc29";
        }
    }
});