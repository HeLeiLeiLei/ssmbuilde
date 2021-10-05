<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.min.js"></script>

    <script>
        function bookQuery() {

            alert($("input[name='CrrentPage']").val());
            $.ajax({
                url:"${pageContext.request.contextPath}/book/queryBook",
                data:{"queryBookName":$("#qBookName").val(),"CrrentPage":$("#index").val()},
                success:function(data){
                    var html="";
                    for (var i=0;i<data.length;i++){
                        html+="<tr>"+
                            "<td>"+"<input type='checkbox' name='ids' value='"+data[i].bookID+"'}>"+data[i].bookID+"</td>"+
                            "<td>"+data[i].bookName+"</td>"+
                            "<td>"+data[i].bookCounts+"</td>"+
                            "<td>"+data[i].detail+"</td>"+
                            "<td>"+"<a href='${pageContext.request.contextPath}/book/toUpdate?bookID="+data[i].bookID+"'>更改</a>"+"</td>"+
                            "<td>"+"<a href='${pageContext.request.contextPath}/book/deleteBookById/"+data[i].bookID+"'>删除</a>"+"</td>"+
                            "</tr>"
                    }
                    $("#content").html(html);
                },
            })
        }
        
        function deletByIds() {
            var arr=[];
            $("input:checkbox[name='ids']:checked").map(function(index,elem) {
                arr.push($(elem).val());
            });
            alert(arr)
            if(arr.length != 0){
                $.ajax({
                    url:"${pageContext.request.contextPath}/book/toDeleteBooks",
                    data:{"ids":arr},
                    traditional:true,
                    success:function (data) {
                        var html="";
                        for (var i=0;i<data.length;i++){
                            html+="<tr>"+
                                "<td>"+"<input type='checkbox' name='ids' value='data[i].bookID'}'>"+data[i].bookID+"</td>"+
                                "<td>"+data[i].bookName+"</td>"+
                                "<td>"+data[i].bookCounts+"</td>"+
                                "<td>"+data[i].detail+"</td>"+
                                "<td>"+"<a href='${pageContext.request.contextPath}/book/toUpdate?bookID="+data[i].bookID+"'>更改</a>"+"</td>"+
                                "<td>"+"<a href='${pageContext.request.contextPath}/book/deleteBookById/"+data[i].bookID+"'>删除</a>"+"</td>"+
                                "</tr>"
                        }
                        $("#content").html(html);
                    }
                });
            }else {
                alert("请选择要删除的选项")
            }

        }
    </script>
</head>

<body>

<div class="container">

        <div><h3>${msg}</h3></div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表 —— 显示所有书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增</a>
            <input type="button" id="btn_deletIds" class="btn btn-primary" value="批量删除" onclick="deletByIds()"/>
            <div class="form-group">
                <input type="text" name="bookName" id="qBookName" class="form-control" placeholder="请输入查询的名字"/>
                <button type="button" id="btn" onclick="bookQuery()">查询</button>
            </div>
        </div>
    </div>


    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>书籍编号</th>
                    <th>书籍名字</th>
                    <th>书籍数量</th>
                    <th>书籍详情</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody id="content">
                <c:forEach var="book" items="${list}">
                    <tr>
                        <td><input type="checkbox" name="ids" id="ids" value="${book.getBookID()}">${book.getBookID()}</td>
                        <td>${book.getBookName()}</td>
                        <td>${book.getBookCounts()}</td>
                        <td>${book.getDetail()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/book/toUpdate?bookID=${book.getBookID()}">更改</a> |
                            <a href="${pageContext.request.contextPath}/book/deleteBookById/${book.getBookID()}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>


        </div>
    </div>
        <div class="row clearfix">
            <div class="col-md-12 column">
                <ul class="pagination">
                    <li>
                        <a onclick="bookQuery()">首页</a>
                        <input name="CrrentPage" value="2" type="hidden"/>
                    </li>
                    <li>
                        <a onclick="bookQuery()">上一页</a>
                        <input name="CrrentPage" value="3" type="hidden"/>
                    </li>
                    <li>
                        <a onclick="bookQuery()" value="${CrrentPage+1}" name="CrrentPage">下一页</a>
                        <input name="CrrentPage" value="4" type="hidden"/>
                    </li>
                    <li>
                        <a onclick="bookQuery()" value="${end}" name="CrrentPage">尾页</a>
                        <input name="CrrentPage" value="5" type="hidden"/>
                    </li>
                    <li>
                        <a>当前为第 ${crrentPage} 页 共  ${totalPage}  页</a>
                    </li>
                </ul>
            </div>
        </div>

</div>
</body>


