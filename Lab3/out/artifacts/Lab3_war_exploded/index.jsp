<%@ page import="javax.naming.InitialContext,javax.rmi.PortableRemoteObject,java.math.BigDecimal,
java.rmi.RemoteException" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.ejb.CreateException" %>
<%@ page import="lab3.ConverterHome" %>
<%@ page import="lab3.Converter" %>
<%!
  private Converter converter = null;
  public void jspInit() {
    try {
      InitialContext ic = new InitialContext();
      Object objRef = ic.lookup("java:comp/env/ejb/TheConverter");
      ConverterHome home =
              (ConverterHome)PortableRemoteObject.narrow(objRef, ConverterHome.class);
      converter = home.create();
    } catch (RemoteException | NamingException | CreateException ex) {
        ex.printStackTrace();
    }
  }
%>
<html>
<head>
  <title>lab3.Converter</title>
</head>

<body bgcolor="white">
<h1><center>lab3.Converter</center></h1>
<hr>
<p>Enter an amount to convert:</p>
<form method="get">
  <label>
    <input type="text" name="amount" size="25">
  </label>
  <br>
  <p>
    <input type="submit" value="Submit">
    <input type="reset" value="Reset">
</form>
<%
  String amount = request.getParameter("amount");
  if (amount != null && amount.length() > 0) {
    BigDecimal d = new BigDecimal(amount);
%>
<p><%= amount %> dollars are
    <%= converter.dollarToYen(d) %> Yen.
<p><%= amount %> Yen are
    <%= converter.yenToEuro(d) %> Euro.
    <%
}
%>
</body>
</html>
