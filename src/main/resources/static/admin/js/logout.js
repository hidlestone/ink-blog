layui.use('layer', function() {
});

$(function(){
    $('#logout').on('click', function () {
        $.ajax({
            url:"/admin/logout",
            method:"post",
            success:function (data) {
                if(data.success){
                    setTimeout(
                        function(){location.href = "/admin/login";}, 500);
                }
            }
        })
    });
})


