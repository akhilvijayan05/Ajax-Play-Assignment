$(document).ready(function() {

    $('#front').click(function(e) {
      frontCall();
    });
    $('#signin').click(function(e) {
      signinCall();
    });
    $('#signup').click(function(e) {
      signupCall();
    });
});

var frontCall = function() {
    var ajaxCallBack = {
        success : function(data) {$('#data').html(data);},
        error : function(error) {alert(error);}
    }

    jsRoutes.controllers.DetailController.detail().ajax(ajaxCallBack);
};

var signinCall = function() {
    var ajaxCallBack = {
        success : function(data) {$('#data').html(data);},
        error : function(error) {alert(error);}
    }

    jsRoutes.controllers.FrontController.signin().ajax(ajaxCallBack);
};

var signupCall = function() {
    var ajaxCallBack = {
        success : function(data) {$('#data').html(data);},
        error : function(error) {alert(error);}
    }

    jsRoutes.controllers.FrontController.signup().ajax(ajaxCallBack);
};

