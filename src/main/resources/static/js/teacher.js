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
        lessonAddPageShow : false
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
           console.log(_this.teacherInfo.id);
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
        }
    }
});