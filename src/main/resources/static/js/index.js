var loginAndRegister = new Vue({
    el : "#loginOrRegister",
    data : {
        showLogin : true,
        showRegister : false,
        loginData: {
            username: "",
            password: "",
            rememberPassword: true
        },
        registerData : {
            username : "",
            password : "",
            repeatPassword : "",
            email : "",
            name : "",
            department_id : "",
            major_id : "",
            level : ""
        },
        departmentData : {
            id : "",
            name : ""
        },
        majorData : {
            id : "",
            name : ""
        }
    },
    mounted : function () {
        this.getDepartmentData();
    },
    watch : {
        'registerData.department_id' :{handler : function (newVal, oldVal) {
            // console.log("do it");
            // console.log("new val : " + newVal);
            // console.log("oldVal : " + oldVal);
            this.getMajorData();
        }, deep: true}
    },
    methods : {
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
            if(_this.registerData.department_id === ""){
                return;
            }
            axios.get("/service/"+ _this.registerData.department_id +"/major").then(function (reponse) {
                _this.majorData = reponse.data.data;
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            });
        },
        setTokenCookie : function (data) {
            var day = new Date();
            day.setTime(day.getTime() + 1000*3600*72);
            var aliveTime = ";expires=" + day;
            data = JSON.parse(data);
            for(var key in data){
                document.cookie = key + "=" + data[key] + aliveTime;
            }
        },
        login : function () {
            var identity = header.getCookieValue("level");
            console.log(identity)
            switch (identity){
                case "1" : window.location.href = "/student.html"; break;
                case "2" : console.log(1);window.location.href = "/teacher.html"; break;
            }
        },
        loginCheck : function () {
            var _this = this;
            var params = header.requestDataParser(_this.loginData);
            axios.post("/service/loginCheck", params).then(function (response) {
                if(response.data.status.code === 200){
                    _this.setTokenCookie(response.data.data);
                    _this.login();
                }else {
                    alert("请检查用户名和密码是否正确");
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            });
        },
        register : function () {
            var _this = this;
            var params = header.requestDataParser(_this.registerData);
            axios.post("/service/register/" + _this.registerData.level, params).then(function (response) {
                if (response.data.status.code === 200) {
                    _this.setTokenCookie(response.data.data);
                    _this.login();
                }
            }).catch(function (error) {
                console.log(error);
                alert("网络错误");
            });
        },
        changeDisplay : function () {
            this.showLogin = !this.showLogin;
            this.showRegister = !this.showRegister;
        }
    }
});


