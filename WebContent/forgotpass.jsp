<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<section>
<br/><br/><br/>
        <form class="form-horizontal" action="UserController" method="post">
        
            <input type="hidden" name="action" value="forgot" />
            <div class="form-group">
            <label class="col-sm-4 control-label">Email *</label>
            <div class="col-sm-4">
            <input type="email" class="form-control" name="email" required value="<c:out value='${email}'/>"/>
            <c:if test="${msg ne null }">
			   <br/>
            <div class="alert alert-danger"> <c:out value='${msg}'/> <span class="glyphicon glyphicon-remove"></span></div>
            </c:if>
            </div>
            </div>
			<div class="form-group">
    <div class="col-sm-offset-5">
            <input type="submit" value="Reset Password" class="btn btn-primary">
            </div>
            </div>
            <br><br/><br/>
        </form>
        </section>
  
<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>