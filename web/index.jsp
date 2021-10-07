<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <meta charset="utf-8">
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css">
</head>

<body style="background-color:rgb(255,255,255)">
<div class="content" style="border-top:1px solid #000;
								border-bottom:1px solid #000">
  <form action="${pageContext.request.contextPath}/book/toHello">
    <div class="form sign-in">
      <h2>欢迎回来</h2>
      <label>
        <span>邮箱</span>
        <input type="email" required/>
      </label>
      <label>
        <span>密码</span>
        <input type="password" />
      </label>
      <button type="submit" class="submit">登 录</button>
    </div>
  </form>
  <div class="sub-cont">
    <div class="img">
      <div class="img__text m--up">
        <h2>还未注册？</h2>
        <p>立即注册，发现大量机会！</p>
      </div>
      <div class="img__text m--in">
        <h2>已有帐号？</h2>
        <p>有帐号就登录吧，好久不见了！</p>
      </div>
      <div class="img__btn">
        <span class="m--up">注 册</span>
        <span class="m--in">登 录</span>
      </div>
    </div>
    <form action="" >
      <div class="form sign-up">
        <h2>立即注册</h2>
        <label>
          <span>用户名</span>
          <input type="text" required/>
        </label>
        <label>
          <span>邮箱</span>
          <input type="email" required/>
        </label>
        <label>
          <span>密码</span>
          <input type="password" required/>
        </label>
        <button type="button" class="submit">注 册</button>
      </div>
    </form>
  </div>
</div>

<script src="${pageContext.request.contextPath}/statics/js/script.js"></script>
<style>
  .copyrights{text-indent:-9999px;height:0;line-height:0;font-size:0;overflow:hidden;}
</style>

</body>

</html>