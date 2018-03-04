Vue.component("header-template",{
    props : ['showExit'],
    template : "<div class='headerBoundary'>" +
    "<div class='pDiv'><p class='pStyle'>计算机学院考试系统</p></div>" +
    "<div class='buttonDiv'>" +
    "<button v-if='showExit' class='buttonElement'>退出登陆</button>" +
    "</div>" +
    "</div>"
});
Vue.component("footer-template", {
    template : "<div class='footerBoundary'></div>"
});

var header = new Vue({
    el: "#header",
    data: {
        showExit : false
    },
    methods : {
        requestDataParser : function (data) {
            var requestData = new URLSearchParams();
            for(var key in data) {
                requestData.append(key, data[key]);
            }
            return requestData;
        },
        requestDataParserOfGet : function (data) {
            var requestData = '?';
            for (var key in data){
                if(data[key] !== ''){
                    requestData += key + '=' + data[key] + '&';
                }
            }
            if (requestData[requestData.length] === '&'){
                requestData[requestData.length] = '';
            }
            return requestData;
        },
        getCookieValue : function (key) {
            var arrayCookie = document.cookie.split("; ");
            for (var i=0; i<arrayCookie.length; i++){
                var elementCookie = arrayCookie[i].split("=");
                if(key === elementCookie[0]){
                    return arrayCookie[i].substring(key.length + 1);
                }
            }
            return "";
        },
        toWelcomePage : function () {
            window.location.href = "/index.html";
        },
        getTimeFormat : function (date) {
            var d = new Date(date);
            var offSetMill = d.getTimezoneOffset() * 60000;
            d = new Date(d.getTime() + offSetMill);
            return d.toLocaleDateString() + " " + d.toLocaleTimeString();
        }
    }
});

var footer = new Vue({
    el : "#footer",
    data : {}
});
