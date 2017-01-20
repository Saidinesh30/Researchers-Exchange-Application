
   <br/>
  <br/>
  <br/>
  <br/>
  <br/>
<%-- Section to display description --%>
<section class="copyright">
    &copy; Researchers Exchange Participations &nbsp;
   <%
     try{   
       Cookie[] cookies=null;
        cookies=request.getCookies();
        for(Cookie c: cookies)
        {
        	if(c.getName().equals("host"))
        	{
            %>
          <%=c.getName()%> :: <%=c.getValue()%>
            <%
        	}
        	if(c.getName().equals("port"))
        	{
            %>
          <%=c.getName()%> :: <%=c.getValue()%>
            <%
        	}
            
        }
     }catch(NullPointerException e){
      e.printStackTrace();
     }

    %>
  
</section>
</body>
</html>

