<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<section>
<br/><br/><br/>
        <form class="form-horizontal" action="StudyController" method="post">
        
            <input type="hidden" name="action" value="recommended" />
            <div class="form-group">
            <label class="col-sm-4 control-label">Name *</label>
            <div class="col-sm-4">
            <input type="text" class="form-control" name="name" required value="<c:out value='${name}'/>"/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Email *</label>
            <div class="col-sm-4">
            <input type="email" class="form-control" name="email" required value="<c:out value='${email}'/>"/>
            <input type="hidden" class="form-control" name="recommenderemail" required value="<c:out value='${email}'/>"/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Password *</label>
            <div class="col-sm-4">
            <input type="password" class="form-control" name="password" required/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Confirm Password *</label> 
            <div class="col-sm-4">
            <input type="password" class="form-control" name="confirm_password" required />  
            <c:if test="${msg ne null }"><br/>
            <div class="alert alert-danger"> <c:out value='${msg}'/> <span class="glyphicon glyphicon-remove"></span></div>
            </c:if>
            </div>
			</div>
			<div class="form-group">
    <div class="col-sm-offset-5">
            <input type="submit" value="Create Account" class="btn btn-primary">
            </div>
            </div>
            <br><br/><br/>
        </form>
        </section>
  
<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>