var teacher = new Vue({
    el : "#test",
    data : {
        pageShowController : {
            lessonPage : true,
            testPaperPage : false,
            scorePage : false
        },
        teacherInfo : {
            id : "",
            username : "",
            name : "",
            department_name : "",
            major_name : ""
        },
        newLesson : {
            name : "",
            teacher_id : "",
            department_id : "",
            major_id : ""
        },
        lessonsInfo : {
            id : "",
            name : "",
            // teacher_id : "",
            teacher_name : "",
            // department_id : "",
            department_name : "",
            // major_id : "",
            major_name : "",
            time : "",
            status : ""
        },
        lessonQueryParams : {
            id : "",
            name : "",
            teacher_id : "",
            department_id : "",
            major_id : "",
            status : ""
        },
        departmentData : {
            id : "",
            name : ""
        },
        majorData : {
            id : "",
            name : ""
        },
        lessonAddPageShow : false,
        exams : {
            id : "",
            name : "",
            lesson_name : "",
            teacher_name : "",
            department_name : "",
            major_name : "",
            time : "",
            duration : ""
        },
        examQueryParams : {
            teacher_id : "",
            department_id : "",
            major_id : "",
            lesson_id : "",
            name : ""
        },
        newExam : {
            id : "",
            name : "",
            lesson_id : "",
            releaseTime : "",
            duration : ""
        },
        examAddPageShow : false,
        examEditControl : false,
        examAddControl : false,
        examQuestionEditControl : false,
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
        examChoiceQuestionAddDivControl : false,
        examObjectiveQuestionAddDivControl : false,
        newChoiceQuestion : {
            description : "",
            optionA : "",
            optionB : "",
            optionC : "",
            optionD : "",
            answer : "",
            score : "",
            exam_id : ""
        },
        newObjectiveQuestion : {
            description : "",
            score : "",
            exam_id : ""
        },
        choiceQuestions : {
            examInfoId : "",
            id : "",
            description : "",
            optionA : "",
            optionB : "",
            optionC : "",
            optionD : "",
            answer : "",
            score : ""
        },
        objectiveQuestions : {
            examInfoId : "",
            id : "",
            description : "",
            score : ""
        },
        examOfFinished : {
            id : "",
            name : "",
            lesson_name : "",
            teacher_name : "",
            department_name : "",
            major_name : "",
            time : "",
            duration : ""
        },
        studentInfoOfFinishExam: {
            id : "",
            username : "",
            name : "",
            department_name : "",
            major_name : "",
            score_choice : "",
            score_objective : "",
            score_max : ""
        },
        studentInfoOfFinishExamControl : false,
        answerOfStudentDivControl : false,
        objectiveQuestionOfStudentDone : {
            description : "",
            score : "",
            exam_id : "",
            answer : "",
            scoreOfGet : ""
        },
        answers : {
            user_id : "",
            exam_id : "",
            choiceAnswers : [],
            objectiveAnswers : []
        },
        scoreOfStudent : {
            student_id : "",
            exam_id : "",
            score : 0
        },
        studentInfoIsDone: {
            id : "",
            username : "",
            name : "",
            department_name : "",
            major_name : ""
        },
        getHistoryControl : {
           choice : false,
           objective : false
        },
        historyParam : {
            departmentId : "",
            majorId : "",
            type : ""
        },
        historyChoiceQuestions : {
            id : "",
            description : "",
            optionA : "",
            optionB : "",
            optionC : "",
            optionD : "",
            answer : "",
            score : ""
        },
        historyObjectiveQuestions : {
            id : "",
            description : "",
            score : ""
        }
    },
    mounted : function () {
        this.getTeacherInfo();
    },
    watch : {
        'newLesson.department_id' :{handler : function () {
            this.getMajorData();
        }, deep: true},
        'teacherInfo.id' :{handler : function () {
            this.getLessonsInfo();
        }, deep: true},
        'pageShowController.lessonPage' :{handler : function () {
            if(this.pageShowController.lessonPage) {
                this.getLessonsInfo();
            }
        }, deep: true},
        'pageShowController.testPaperPage' :{handler : function () {
            if(this.pageShowController.testPaperPage) {
                this.getExamList();
            }
        }, deep: true},
        'pageShowController.scorePage' :{handler : function () {
            if(this.pageShowController.scorePage) {
                this.getFinishedExam();
            }
        }, deep: true},
        'getHistoryControl.choice' :{handler : function () {
            if(this.getHistoryControl.choice) {
                this.getHistory(1);
                this.getDepartmentData();
            }else {
                this.getExamQuestionList(this.examInfoOfEdited.id, 1);
            }
        }, deep: true},
        'getHistoryControl.objective' :{handler : function () {
            if(this.getHistoryControl.objective) {
                this.getHistory(2);
                this.getDepartmentData();
            }
            else {
                this.getExamQuestionList(this.examInfoOfEdited.id, 2);
            }
        }, deep: true},
        'historyParam.departmentId' :{handler : function () {
            this.getMajorDataOfHistoryQuestion();
        }, deep: true}
    },
    methods : {
        switchPage : function (pageName) {
            for(var key in this.pageShowController){
                this.pageShowController[key] = false;
            }
            switch (pageName){
                case "lessonPage" : this.pageShowController.lessonPage = true; break;
                case "testPaperPage" : this.pageShowController.testPaperPage = true; break;
                case "scorePage" : this.pageShowController.scorePage = true; break;
            }
        },
        getDepartmentData : function () {
            var _this = this;
            axios.get("/service/department").then(function (reponse) {
                _this.departmentData = reponse.data.data;
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            });
        },
        getMajorDataOfHistoryQuestion : function () {
            var _this = this;
            if(_this.historyParam.departmentId === ""){
                return;
            }
            axios.get("/service/"+ _this.historyParam.departmentId +"/major").then(function (reponse) {
                _this.majorData = reponse.data.data;
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            });
        },
        getMajorData : function () {
            var _this = this;
            if(_this.newLesson.department_id === ""){
                return;
            }
            axios.get("/service/"+ _this.newLesson.department_id +"/major").then(function (reponse) {
                _this.majorData = reponse.data.data;
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            });
        },
        showLessonAddPage : function () {
            this.lessonAddPageShow = !(this.lessonAddPageShow);
            if(this.lessonAddPageShow === true){
                this.getDepartmentData();
                this.newLesson.teacher_id = this.teacherInfo.id;
            }
        },
        getTeacherInfo : function () {
            var _this = this;
            var username = header.getCookieValue("username");
            axios.get("/service/teacher/info/"+username).then(function (response) {
                if(response.data.status.code === 200) {
                    _this.teacherInfo = response.data.data;
                }else {
                    header.toWelcomePage();
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络异常");
            });
        },
        getLessonsInfo : function () {
           var _this = this;
           _this.lessonQueryParams.teacher_id = _this.teacherInfo.id;
           _this.lessonQueryParams.status = 0;
           var params = header.requestDataParserOfGet(_this.lessonQueryParams);
           axios.get("/service/teacher/lessons" + params).then(function (response) {
               if(response.data.status.code === 200){
                   _this.lessonsInfo = response.data.data;
               }else if(response.data.status.code === 555){
                   header.toWelcomePage();
               }
               else {
                   alert("课程查询失败");
               }
           }).catch(function (error) {
               console.log(error);
               alert("网络错误");
           })
        },
        addLesson : function () {
            var _this = this;
            var params = header.requestDataParser(_this.newLesson);
            axios.post("/service/teacher/lesson/add", params).then(function (response) {
                if(response.data.status.code === 200){
                    _this.showLessonAddPage();
                    _this.getLessonsInfo();
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }
                else {
                    alert("添加失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络异常");
            })
        },
        deleteLesson : function (lessonId) {
            if(!confirm("确认删除课程？")){
                return;
            }
            var _this = this;
            axios.post("/service/teacher/lesson/delete/" + lessonId).then(function (response) {
                if(response.data.status.code === 200){
                    alert("删除成功！");
                    _this.getLessonsInfo();
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }else if(response.data.status.code === 666){
                    alert("请求非法");
                }
                else {
                    alert("删除失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        getExamList : function () {
            var _this = this;
            _this.examQueryParams.teacher_id = _this.teacherInfo.id;
            var params = header.requestDataParserOfGet(_this.examQueryParams);
            axios.get("/service/teacher/exams" + params).then(function (response) {
                if(response.data.status.code === 200){
                    _this.exams = response.data.data;
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
        editExam : function () {
            var _this = this;
            var params = header.requestDataParser(_this.newExam);
            axios.post("/service/teacher/exam/update", params).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    alert("修改考试信息成功");
                    _this.showExamAddPage();
                    _this.getExamList();
                }else if(response.data.status.code === 777){
                    alert("考试已开始,不可修改");
                }
                else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("修改考试信息失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        deleteExam : function (examId) {
            if(!confirm("删除考试？")){
                return;
            }
            var _this = this;
            axios.post("/service/teacher/exam/delete/" + examId).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    alert("删除成功");
                    _this.getExamList();
                }else if(response.data.status.code === 777){
                    alert("考试已开始,不可修改");
                }
                else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("修改失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        addExam : function () {
            var _this = this;
            _this.newExam.id = "";
            var params = header.requestDataParser(_this.newExam);
            axios.post("/service/teacher/exam/add", params).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    alert("添加考试成功");
                    _this.showExamAddPage();
                    _this.getExamList();
                }else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("添加考试失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        showExamAddPage : function () {
            this.examAddPageShow = !(this.examAddPageShow);
            if(this.examAddPageShow === true){
                this.examEditControl = false;
                this.examAddControl = true;
                this.getLessonsInfo();
            }
        },
        showExamEditPage : function (data) {
            this.examAddPageShow = !(this.examAddPageShow);
            if(this.examAddPageShow === true){
                this.examAddControl = false;
                this.examEditControl = true;
                this.newExam = data;
                this.getLessonsInfo();
            }
        },
        doExamEdit : function () {
            if(this.examAddControl){
                this.addExam();
            }
            if(this.examEditControl){
                this.editExam();
            }
        },
        showExamQuestionEditDiv : function (examInfo) {
            var _this = this;
            _this.examQuestionEditControl = !(_this.examQuestionEditControl);
            if(_this.examQuestionEditControl === true){
                _this.examInfoOfEdited = examInfo;
                _this.getExamQuestionList(examInfo.id, 1);
                _this.getExamQuestionList(examInfo.id, 2);
            }
        },
        showNewChoiceQuestionAddDiv : function () {
            this.examChoiceQuestionAddDivControl = !(this.examChoiceQuestionAddDivControl);
        },
        showNewObjectiveQuestionAddDivControl : function () {
            this.examObjectiveQuestionAddDivControl = !(this.examObjectiveQuestionAddDivControl);
        },
        getExamQuestionList : function (examId, type) {
            var _this = this;
            axios.get("/service/teacher/question/"+examId+"/"+type).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    if(type === 1){
                        _this.choiceQuestions = response.data.data;
                    }else if(type === 2){
                        _this.objectiveQuestions = response.data.data;
                    }
                }else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        addNewChoiceQuestion : function () {
            var _this = this;
            _this.newChoiceQuestion.exam_id = _this.examInfoOfEdited.id;
            var params = header.requestDataParser(_this.newChoiceQuestion);
            axios.post("/service/teacher/question/choice/add", params).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    alert("添加选择题成功");
                    _this.showNewChoiceQuestionAddDiv();
                    _this.getExamQuestionList(_this.examInfoOfEdited.id, 1);
                }else if(response.data.status.code === 777){
                    alert("考试已开始,不可修改");
                }
                else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("添加选择题失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        addNewObjectiveQuestion : function () {
            var _this = this;
            _this.newObjectiveQuestion.exam_id = _this.examInfoOfEdited.id;
            var params = header.requestDataParser(_this.newObjectiveQuestion);
            axios.post("/service/teacher/question/objective/add", params).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    alert("添加客观题题成功");
                    _this.showNewObjectiveQuestionAddDivControl();
                    _this.getExamQuestionList(_this.examInfoOfEdited.id, 2);
                }else if(response.data.status.code === 777){
                    alert("考试已开始,不可修改");
                }
                else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("添加客观题失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        deleteExamQuestion : function (id, type, examId) {
            if(!confirm("删除考试？")){
                return;
            }
            var _this = this;
            axios.post("/service/teacher/question/delete/" + examId + "/" + id).then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                    alert("删除成功");
                    if(type === 1) {
                        _this.getExamQuestionList(_this.examInfoOfEdited.id, 1);
                    }else if(type === 2){
                        _this.getExamQuestionList(_this.examInfoOfEdited.id, 2);
                    }
                }else if(response.data.status.code === 777){
                    alert("考试已开始,不可修改");
                }
                else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("删除失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        getFinishedExam : function () {
            var _this = this;
            _this.examQueryParams.teacher_id = _this.teacherInfo.id;
            var params = header.requestDataParserOfGet(_this.examQueryParams);
            axios.get("/service/teacher/exam/finished" + params).then(function (response) {
                if(response.data.status.code === 200){
                    _this.examOfFinished = response.data.data;
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
        getStudentInfoOfFinishExam : function (examId) {
            var _this = this;
            axios.get("/service/teacher/studentInfo/" + examId).then(function (response) {
                if(response.data.status.code === 200){
                    _this.studentInfoOfFinishExam = response.data.data;
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }
                else {
                    alert("查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        studentInfoDivSwitch : function (exam) {
            this.studentInfoOfFinishExamControl = !(this.studentInfoOfFinishExamControl);
            if(this.studentInfoOfFinishExamControl){
                this.getStudentInfoOfFinishExam(exam.id);
                this.examInfoOfEdited = exam;
            }
        },
        getObjectiveQuestionOfStudentDone : function (examId, studentId) {
            var _this = this;
            axios.get("/service/teacher/question/correct/"+examId+"/2").then(function (response) {
                var responseCode = response.data.status.code;
                if(responseCode === 200){
                   _this.objectiveQuestionOfStudentDone = response.data.data;
                   _this.getStudentAnswer(studentId, examId);
                }else if(responseCode === 555){
                    header.toWelcomePage();
                }else {
                    alert("查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        getStudentAnswer : function (studentId, examId) {
            var _this = this;
            axios.get("/service/teacher/answer/" + examId + "/" +studentId).then(function (response) {
                if(response.data.status.code === 200){
                    if(response.data.data.length > 0) {
                        _this.answers = response.data.data[0];
                        _this.setObjectiveAnswer(_this.answers);
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
            for(var i=0; i<this.objectiveQuestionOfStudentDone.length; i++){
                this.objectiveQuestionOfStudentDone[i].answer = Base.decode(this.getAnswerById(this.objectiveQuestionOfStudentDone[i].id, objectives));
            }
        },
        answerOfStudentDivSwitch : function (student) {
            this.answerOfStudentDivControl = !(this.answerOfStudentDivControl);
            if(this.answerOfStudentDivControl === true){
                this.studentInfoIsDone = student;
                this.getObjectiveQuestionOfStudentDone(this.examInfoOfEdited.id, student.id);
            }else {
                this.getStudentInfoOfFinishExam(this.examInfoOfEdited.id);
            }
        },
        submitAnswer : function () {
            var _this = this;
            _this.scoreOfStudent.exam_id = _this.examInfoOfEdited.id;
            _this.scoreOfStudent.student_id = _this.studentInfoIsDone.id;
            _this.scoreOfStudent.score = 0;
            for(var i=0; i<_this.objectiveQuestionOfStudentDone.length; i++){
                var index = i + 1;
                if(_this.objectiveQuestionOfStudentDone[i].scoreOfGet === ""){
                    alert("题目 " + index + " 未判分!!!");
                    return;
                }
                if(_this.objectiveQuestionOfStudentDone[i].scoreOfGet < 0
                    || _this.objectiveQuestionOfStudentDone[i].scoreOfGet > _this.objectiveQuestionOfStudentDone[i].score){
                    alert("题目 " + index + " 判分不合法!!!");
                    return;
                }
                _this.scoreOfStudent.score += _this.objectiveQuestionOfStudentDone[i].scoreOfGet - 0;
                if(isNaN(_this.scoreOfStudent.score)){
                    alert("题目 " + index + " 未判分!!!");
                    return;
                }
            }
            var params = header.requestDataParser(_this.scoreOfStudent);
            axios.post("/service/teacher/exam/score", params).then(function (response) {
                if(response.data.status.code === 200){
                    alert("上传成绩成功");
                    _this.answerOfStudentDivSwitch("");
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }
                else {
                    alert("上传成绩失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        toHistoryChoicePage : function () {
            this.getHistoryControl.choice = !(this.getHistoryControl.choice);
        },
        toHistoryObjectivePage : function () {
            this.getHistoryControl.objective = !(this.getHistoryControl.objective);
        },
        getHistory : function (type) {
            var _this = this;
            _this.historyParam.type = type;
            var params = header.requestDataParserOfGet(_this.historyParam);
            axios.get("/service/teacher/history/" + type + params).then(function (response) {
                if(response.data.status.code === 200){
                    if(type === 1) {
                        _this.historyChoiceQuestions = response.data.data;
                    }
                    if(type === 2){
                        _this.historyObjectiveQuestions = response.data.data;
                    }
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }else {
                    alert("历史题库查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        },
        addHistoryQuestion : function (type, qid) {
            if(!confirm("确认添加此试题？")){
                return;
            }
            var _this = this;
            var params = {"examId":_this.examInfoOfEdited.id,
                            "type":type,
                "questionId":qid
            };
            params = header.requestDataParser(params);
            axios.post("/service/teacher/addHistory", params).then(function (response) {
                if(response.data.status.code === 200){
                    alert("添加成功");
                    if(!confirm("继续添加？")){
                        if(type === 1){
                            _this.toHistoryChoicePage();
                        }
                        if(type === 2){
                            _this.toHistoryObjectivePage();
                        }
                    }
                }else if(response.data.status.code === 555){
                    header.toWelcomePage();
                }else {
                    alert("历史题库查询失败");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            })
        }
    }
});