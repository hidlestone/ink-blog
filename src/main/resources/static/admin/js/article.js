layui.use('layer', function () {
    //    http://bh-lay.github.io/mditor/
    var mditor = Mditor.fromTextarea(document.getElementById('md_editor'));
    mditor.height = '550px';
});

//status:publish、draft
function subArticle(status) {
    var title = $('#articleForm input[name=title]').val();
    var content = $('#md_editor').val();
    if (title == '') {
        layer.msg('请输入文章标题');
        return;
    }
    if (content == '') {
        layer.msg('请输入文章内容');
        return;
    }
    //选中的分类
    var selCategoryId = formSelects.value('select_category', 'valStr');
    //选中的tag
    var selTagIdArr = formSelects.value('select_tag', 'val');
    // alert("selCategoryId：" + selCategoryId)
    // alert("selTagIdArr：" + selTagIdArr)
    if (!selCategoryId) {
        layer.msg('请选择文章分类');
        return;
    }
    if ((!selCategoryId) || (selTagIdArr.length <= 0)) {
        layer.msg('请至少选择一个文章标签');
        return;
    }

    if (status == 'post') {
        $("#status").val(1);
    } else {
        $("#status").val(0);
    }

    var params = {
        "articleId": $("#articleId").val(),
        "articleMetaCatId": selCategoryId,
        "tagList": selTagIdArr,
        "title": $("#title").val(),
        "slug": $("#slug").val(),
        "thumbImg": "",
        "content": content,
        "articleType": status,
        "articleStatus": $("#status").val(),
        "editorType": "markdown",
        "allowComment": $("#allow_comment").val(),
        "allowPing": $("#allow_ping").val(),
        "allowFeed": $("#allow_feed").val(),
        "allowThumbImg": $("#allow_thumb_img").val(),
        "thumbImg": $('#thumbImgUrl').val()
    }
    // var params = $("#articleForm").serialize(); 

    var url = $('#articleForm #articleId').val() != '' ? '/admin/articles/update' : '/admin/articles/publish';
    $.ajax({
        url: url,
        method: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',//json 返回值类型
        data: JSON.stringify(params),
        success: function (result) {
            layer.msg(result.message);
            setTimeout(function () {location.href = "/admin/articles";}, 1000);
        }
    })
}

function returnMenu() {
    history.go(-1)
}


function allowcomment() {
    if ($('#comment').is(':checked')) {
        $('#allow_comment').val("1");
    } else {
        $('#allow_comment').val("0");
    }
}

function allowping() {
    if ($('#ping').is(':checked')) {
        $('#allow_ping').val("1");
    } else {
        $('#allow_ping').val("0");
    }
}

function allowfeed() {
    if ($('#rss').is(':checked')) {
        $('#allow_feed').val("1");
    } else {
        $('#allow_feed').val("0");
    }
}

function allowThumbImgBtn() {
    if ($('#thumbImg').is(':checked')) {
        $('#allow_thumb_img').val("1");
        $('#thumbImgUrl').attr("readonly",false)
    } else {
        $('#thumbImgUrl').val('');
        $('#allow_thumb_img').val("0");
        $('#thumbImgUrl').attr("readonly",true)
    }
}
