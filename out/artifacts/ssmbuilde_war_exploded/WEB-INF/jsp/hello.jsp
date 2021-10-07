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
        function bookQuery(crrentPage) {
            //alert($("input[name='CrrentPage']").val());
            $.ajax({
                url:"${pageContext.request.contextPath}/book/queryBook",
                data:{"queryBookName":$("#qBookName").val(),
                    "CrrentPage":crrentPage,
                },
                success:function(data){
                    //alert(JSON.stringify(data.list));
                    var html="";
                    var list = data.list;
                    for (var i=0;i<list.length;i++){
                        html+="<tr>"+
                            "<td>"+"<input type='checkbox' name='ids' value='"+list[i].bookID+"'}>"+list[i].bookID+"</td>"+
                            "<td>"+list[i].bookName+"</td>"+
                            "<td>"+list[i].bookCounts+"</td>"+
                            "<td>"+list[i].detail+"</td>"+
                            "<td>"+"<a href='${pageContext.request.contextPath}/book/toUpdate?bookID="+list[i].bookID+"'>更改</a>"+"</td>"+
                            "<td>"+"<a href='${pageContext.request.contextPath}/book/deleteBookById/"+list[i].bookID+"'>删除</a>"+"</td>"+
                            "</tr>"
                    }
                    $("#content").html(html);

                    var html2="";
                    html2+=""+
                        "<button onclick=\"bookQuery("+data.pageUtils.index+")\">首页</button>"+
                        "<span>"+"&nbsp;"+"</span>"+
                        "<button onclick=\"bookQuery("+(data.pageUtils.crrentPage+1)+")\">下一页</button>"+
                        "<span>"+"&nbsp;"+"</span>"+
                        "<button onclick=\"bookQuery("+(data.pageUtils.crrentPage-1)+")\">上一页</button>"+
                        "<span>"+"&nbsp;"+"</span>"+
                        "<button onclick=\"bookQuery("+data.pageUtils.end+")\">尾页</button>"+
                        "<span>"+"&nbsp;"+"</span>"+
                        "<span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</span>"+
                        "<span>当前为第</span>"+
                        "<span>"+"&nbsp;&nbsp;"+"</span>"+
                        "<a>"+data.pageUtils.crrentPage+"</a>"+
                        "<a>"+"&nbsp;&nbsp;"+"</a>"+
                        "<span>页</span>"+
                        "<span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</span>"+
                        "<span>共</span>"+
                        "<span>"+"&nbsp;&nbsp;"+"</span>"+
                        "<a>"+data.pageUtils.totalPage+"</a>"+
                        "<span>"+"&nbsp;&nbsp;"+"</span>"+
                        "<span>页</span>"+
                        "<span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</span>"+
                        "<span>共</span>"+
                        "<span>"+"&nbsp;&nbsp;"+"</span>"+
                        "<a>"+data.pageUtils.totalNumber+"</a>"+
                        "<span>"+"&nbsp;&nbsp;"+"</span>"+
                        "<span>条数据</span>"+
                        "";
                    $("#pageDiv").html(html2);
                },
            })
        }
        
        function deletByIds() {
            var arr=[];
            $("input:checkbox[name='ids']:checked").map(function(index,elem) {
                arr.push($(elem).val());
            });
            if(arr.length != 0){
                $.ajax({
                    url:"${pageContext.request.contextPath}/book/toDeleteBooks",
                    data:{"ids":arr},
                    traditional:true,
                    success:function (data) {
                        console.log(data.infor)
                        if(data.infor == "success"){
                            alert("删除成功");
                            window.location.reload();
                        }else {
                            alert("删除失败");
                            lwindow.location.reload();
                        }
                    }
                });
            }else {
                alert("请选择要删除的选项")
            }

        }

    </script>
</head>

<body onload="bookQuery()">

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表 —— <a onclick="bookQuery()">显示所有书籍</a></small>
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
                <!--数据展示区域-->
                </tbody>
            </table>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                </thead>
                <tbody id="pageDiv">
                <!--分页数据展示区域-->
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>


