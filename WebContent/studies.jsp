
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >
 
<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>     
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
            <th>ShareIt</th>
        </tr>
<c:forEach var="sList" items="${studyList}">
        <tr>   
            <%-- First study details --%>
            <td><c:out value='${sList.studyName}'/></td>
            <td><c:out value='${sList.requestedParticipants}'/></td>
            <td><c:out value='${sList.numOfParticipants}'/></td>
            <c:choose>
            <c:when test="${sList.status eq 'start' }">
            <td> <form action="StudyController" method="post"><button type="submit" class="btn btn-primary">Stop</button>    <input type="hidden" name="action" value="stop"> 
             <input type="hidden" name="StudyCode" value="<c:out value='${sList.studyCode}'/>">
              <input type="hidden" name="email" id="email" value="<c:out value='${theUser.email}'/>">
            </form></td>
            </c:when>
            <c:otherwise>
            <td> <form action="StudyController" method="post"><button type="submit" class="btn btn-primary">Start</button>    <input type="hidden" name="action" value="start"> 
             <input type="hidden" name="StudyCode" value="<c:out value='${sList.studyCode}'/>">
              <input type="hidden" name="email" id="email" value="<c:out value='${theUser.email}'/>">
            </form></td>
            </c:otherwise></c:choose>
            <%-- Code to display edit page --%>
            <td><form action="StudyController" method="post">
                    <button type="submit" class="btn btn-primary">Edit</button>
                        <input type="hidden" name="action" value="edit">
                       <input type="hidden" name="StudyCode" value="<c:out value='${sList.studyCode}'/>">
                       <input type="hidden" name="email" value="<c:out value='${theUser.email}'/>">
                    
                    </form></td>
                     <td>
                     <a href="https://www.facebook.com/sharer/sharer.php?u=http://localhost:8084/Assignment3/login.jsp & title=Studycode:${sList.studyCode} 
                   & description=Question:${sList.question} 
                   " target="_blank" class="btn btn-facebook"><i class="fa fa-facebook"></i> Facebook</a>
   
                        
                    </td>        

        </tr>
</c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td> 
            <td></td> 
            <td></td> 
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td> 
            <td></td>
            <td></td> 
            <td></td> 
            <td></td>
        </tr>
    </table>
</div>
</section>
  <br/>
  <br/>
  <br/>
  <br/>
  <br/>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>