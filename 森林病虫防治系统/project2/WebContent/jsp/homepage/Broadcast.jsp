<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/bootstrap-theme.min.css">

    <script src="../../js/jQuery-2.2.2-min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<style>


    #div5 img{
        height: 530px;
        width: 786px;
    }
    #div5{
        padding: 0px;
    }



</style>
 <body>
  <div class="container-fluid">
      <div class="row">
          <div class="col-md-8 " id="div5">
              <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                  <ol class="carousel-indicators">
                      <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                      <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                      <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                  </ol>
                  <div class="carousel-inner">
                      <div class="item active">
                          <img src="../../img/2.png" alt="First slide">
                          <div class="carousel-caption">
                              <h3>害蟲</h3>
                              <p>爲害人類健康</p>

                          </div>
                      </div>
                      <div class="item">
                          <img src="../../img/3.png" alt="Second slide">
                          <div class="carousel-caption">
                              <h3>中科院專家</h3>
                              <p>商討處理方案</p>
                          </div>
                      </div>
                      <div class="item">
                          <img src="../../img/4.png" alt="Third slide">
                          <div class="carousel-caption">
                              <h3>保護森林人人有責</h3>
                              <p>保護好最後一片綠葉</p>
                          </div>
                      </div>
                  </div>
                  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                      <span class="glyphicon glyphicon-chevron-left"></span>
                  </a>
                  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                      <span class="glyphicon glyphicon-chevron-right"></span>
                  </a>
              </div>
          </div>

      </div>

  </div>



</body>
</html>