<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp"></jsp:include>
<!--  
<table class="table table-bordered table-striped"> 
  <div class="container-fluid">
  <tr>
  <td>
  <div class="row">
  <div class="col-sm-2" align="center">角色ID</div>
  <div class="col-sm-2"></div>
  <div class="col-sm-2" align="center">角色名称</div>
  <div class="col-sm-3"></div>
  <div class="col-sm-2" align="center">操作</div>
  </div>
  </td>
  </tr>
  
  <tr> 
  <td>
  <div class="row">
  <div class="col-sm-2" align="center">1</div>
  <div class="col-sm-2"></div>
  <div class="col-sm-2" align="center" >管理员</div>
  <div class="col-sm-3"></div>
  
  <div class="col-sm-2">
  <div class="row">
  <div class="col-sm-6">
 <a href="/dms/admin/role/role_modify.jsp"><center>修改<center></a>
  </div>
  <div class="col-sm-6">
  <a href="javascript:void(0);" onclick="isDelete()"><center>删除<center></a>
  </div>
  </div>
  </div>
  </div>
  </td>
  </tr>

  
  <tr>
  <td>
  <div class="row">
  <div class="col-sm-11" ></div>
  <div class="col-sm-1" ><button type="button" class="btn btn-primary btn-block" onclick="javascript:window.location.href='/dms/admin/role/role_add.jsp'">增加</button></div>
  </div>
  </td>
  </tr>
  
 </div>
 </table>
 -->
<div class="container-fluid">
  <div class="row">
    <div class="col-md-12">
      <div class="table-responsive">
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <td>角色ID</td>
              <td>角色名称</td>
              <td colspan="3">操作</td>
            </tr>
          </thead>
          
          <tbody id="rl">
         
          </tbody>  
        </table>
      </div>
    </div>
    </div>
    
    <div class="row">
    <div class="col-sm-10"></div>
    <div class="col-sm-2">
      <button type="button" class="btn btn-primary btn-block" onclick="javascript:window.location.href='/dms/admin/role/role_add.jsp'">增加角色</button>
    </div>
  </div>
</div>
    

<jsp:include page="/include/footer.jsp"></jsp:include>