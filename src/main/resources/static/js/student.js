var student = new Vue({
    el : "#student",
    data : {
        studentInfo : {
            id : "",
            username : "",
            name : "",
            department_name : "",
            major_name : ""
        }
    },
    mounted : function () {
        this.getStudentInfo();
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
        }
    }
});