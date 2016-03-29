# Project
Group Project for 4413

##SSL

You will need to manually do two things.

1. In Eclipse go to your server.xml file (server project folder).  In here fine the <connector> tag with parameter port="8443".  Add to the end of this tag the parameters keystoreFile="${user.home}/.keystore" keystorePass="abc123".  Now uncomment the tag (<!-- and -->).

2. I've uploaded a .keystore file to github.  Copy the file to your home directory (C://Users/<username> or /home/<username>), do not put it into a subfolder.

Restart your server and test SSL with: https://localhost:8443/

##Login

I have pushed some minor changes to login.jspx to be compatible.  Everything else must modify non-shared files.  

1. Modify web.xml,  insert the following text at the end just before the ```</web-app>``` tag.  This adds a new group of users called "partner", specifies that /home.jspx (change this, it was set as a test) requires this group in order to access, and specifies that /login.jspx has the form for logging in.
```
<security-constraint>
	<web-resource-collection>
		<web-resource-name>Partners</web-resource-name>
		<url-pattern>/temp.jspx</url-pattern>
		<http-method>GET</http-method>
		<http-method>POST</http-method>
	</web-resource-collection>

	<auth-constraint>
		<role-name>partner</role-name>
	</auth-constraint>

	<user-data-constraint>
		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
	</user-data-constraint>
</security-constraint>

<security-constraint>
	<web-resource-collection>
      		<web-resource-name>Admins</web-resource-name>
      		<url-pattern>/home.jspx</url-pattern>
      		<http-method>GET</http-method>
      		<http-method>POST</http-method>
    	</web-resource-collection>

	<auth-constraint>
		<role-name>admin</role-name>
	</auth-constraint>

	<user-data-constraint>
      		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
      	 </user-data-constraint>
  </security-constraint>

<login-config>
	<auth-method>FORM</auth-method>
	<form-login-config>
		<form-login-page>/login.jspx</form-login-page>
		<form-error-page>/login-failed.jspx</form-error-page>
	</form-login-config>
</login-config>
```

2. In context.xml before the ```</context>``` tag insert the following.  Make sure not to delete any existing Resource tag.  This adds a link to the database as a login database and links to the realms (they manage logins for us).

```
	<Resource name="jdbc/auth" description="Sample authentication"
		type="javax.sql.DataSource" auth="Container" driverClassName="org.apache.derby.jdbc.EmbeddedDriver"
		maxActive="10" maxIdle="3" maxWait="10000" url="jdbc:derby:/home/william/Programming/4413/Workspace/Project/DB" />

	<Realm className="org.apache.catalina.realm.DataSourceRealm"
		userTable="APP.accounts" userNameCol="userName" userCredCol="password"
		userRoleTable="user_roles" roleNameCol="role" localDataSource="true"
		dataSourceName="jdbc/auth" />
```

With those changes you should be able to now test the servlet.  The code provided above will create a lock on /home.jspx requiring admin priviledge to view.  Put into the database the following:
```
INSERT INTO accounts (userName, password, account_type) VALUES ('Andy', 'abc123', 'admin');
INSERT INTO user_roles (userName, role) VALUES ('Andy', 'admin');
```

This will now add a user Andy with password abc123 and you should be able to access /home.jspx with those credentials.
