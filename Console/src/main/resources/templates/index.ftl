<!---
  Created by wuhongxu on 2017/1/5 0005.
--->

<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body>
<form id="form" action="http://127.0.0.1:8080/commodity/uploadTest" enctype="multipart/form-data" method="post">
    <input id="file" type="file" name="file"/>选择文件
    <input id="name" type="text" value="name" name="name"/>
    <input type="submit" value="提交" id="sub"/>
</form>
</body>
<script type="text/javascript">
    document.getElementById("sub").addEventListener("click", handleSubmit);
    function handleSubmit() {
        event.preventDefault();
        var data = new FormData(document.getElementById('form'));
        fetch('/commodity/uploadTest', {
            headers: {},
            method: 'post',
            body: data
        }).then(function (response) {
            response.json();
        }).then(function (data) {
            console.log(data);
        });
    }
</script>
</html>