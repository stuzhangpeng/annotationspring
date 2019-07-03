<!--测试ajax交互-->
<!--获取ajax对象的方法-->
function getXhr() {
    var xhr=null;
    if(window.XMLHttpRequest ){
        xhr=new XMLHttpRequest();
    }
    else{
        xhr=new ActiveXObject("Microsoft.XMLHttp")
    }
    return xhr;
}
var xhr=getXhr();
/*
* 建立连接
* */
xhr.open(
    "post",
    "http://localhost:8080/springmvc/testAjax"

);
//发送信息方法
//注意当请求方式为get时，send方法无效单不能省略，所以一般设置为null
//发送的数据拼接到url
//当请求方式为post方式时有用,但是必须设置请求头的contenttype属性
xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.send(
    "name=zhangwuji"

);
xhr.onreadystatechange=function () {
    //  判断当前服务器状态
    if(xhr.readyState==4){
        //判断当前请求的响应是否成功
        if (xhr.status==200){
            //接收响应的数据，数据格式为html格式
            var data=xhr.responseText;
            console.log(data)
        }

    }

}
