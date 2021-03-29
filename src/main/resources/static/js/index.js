
function increaseLikeValue(){
    let userId = document.getElementById('userPostId').value;
    //  alert(userId);
    first_call_to_ajax(userId);
}

function first_call_to_ajax(userId) {
    let id_for_like = {};
    var json={
        'id': userId
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/likeClick",
        data: JSON.stringify(json),
        dataType: 'text',
        cache: false,
        success: function (data){

            document.getElementById("like").value = data;
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function postComment(id){

    var commentId = "comment"+id;
    let commentVal = document.getElementById(commentId).value;
    var json = {
        'id': id,
        'comment': commentVal
    }


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/saveComment",
        data: JSON.stringify(json),
        dataType: 'text',
        cache:false,
        success: function (data){
            alert(data);
        },
        error: function (e){
            console.log(e);
        }
    })
}

