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
    $('#signinbtn').unbind().on("click",function() {

          signinbtnCall();
        });
                $('#signupbtn').unbind().on("click",function(){
                //alert("Hello! I am an alert box!!");
                                  signupbtnCall();
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
var signinbtnCall = function() {
   jsRoutes.controllers.SigninController.check(
                                   $('#username').val(),
                                   $('#password').val()
                   ).ajax({
                               success: function(data){
                                 $('#data').html(data);
                               },
                               error: function(){
                               alert("Oops! Something went wrong");
                             }

                   })

                        return false;
};

var signupbtnCall = function() {
            jsRoutes.controllers.SignupController.store (
                                            $('#firstname').val(),
                                            $('#middlename').val(),
                                            $('#lastname').val(),
                                            $('#username').val(),
                                            $('#password').val(),
                                            $('#repassword').val(),
                                            $('#mobile').val(),
                                            $('#gender').val(),
                                            $('#age').val(),
                                            $('#hobbies').val()
                            ).ajax({
                                        success: function(data){
                                          $('#data').html(data);
                                        },
                                        error: function(){
                                        alert("Oops! Something went wrong");
                                      }

                            })

                                 return false;
                        };
