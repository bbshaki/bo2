let replyService = (function (){
    let list = function(page, callback){
        $.getJSON("/replies/list/" + bno, {page : page}, function (data){
            callback(data)
        })
    }
    let register = function(reply, callback){
        console.log("==========register")
        $.post({
            url : "/replies/new",
            data : JSON.stringify(reply), // json 문자열
            contentType : "application/json; charset=utf-8"
        }, function (data){
            callback(data) // 콘솔창에 data 출력
        });
    }
    let modify = function(reply, callback){
        $.ajax({
            url : "/replies/modify/" + reply.rno,
            data : JSON.stringify(reply),
            type : "put",
            contentType: "application/json; charset=utf-8",
            success : function (result){
                callback(result)
            },
            error : function (result, status, error){
                console.log(result.status + " " + result.error)
            }
        })
    }
    let read = function(rno, callback){
        $.get("/replies/get/" + rno, function (data){
            callback(data)
        })
    }
    let remove = function(){

    }
    return{
        list : list,
        register : register,
        modify : modify,
        read : read,
        remove : remove,
    }

})();