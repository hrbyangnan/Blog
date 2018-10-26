README Team Two Hungry Traveller Blog

1. We have included our original SQL file called Blog.SQL, however if you would like to reload the database we have included an SQL file called group2.SQL which has some data already included.

2. The media button in the WYSIWYG editor can be used to embed youtube videos. However if you wish to upload your own video or audio you should use the "Choose Files" button on the form. We were unable to configure the WYSIWYG photos function with our database so we have disabled the photo function on the editor.

2b. When editing articles, all photos and media for that article are deleted unless you choose to add them back with the "Choose Files".

3. A user is only able to comment once they are logged in, though they are able to view comments.

4. We have popups for incorrect username or password when logging in. If intelliJ is open this will sometimes appear behind the browser window. The page will pause until the popup is clicked.

5.For sending emails for password reset it is important that the tomcat server is 8283 which is the port in the code. If you need to use a different port you have to change GenerateLinkUtils class in the administrative folder.

6. At present the only admin is Henry, and the admin interface and button on navbar will only appear if Henry is logged in.

7. Test accounts 

username	password
---------	-----------
Henry		secret		(admin)
James		password
Anna		123
nyan779		yangnan
kim94		1234


8. Team Two implemented packages 1, 2, 3 and 4. So don't use your real passwords because they are in plaintext.

9. The username and password for the database are already in the connection.properties file. 
If they need to be re-entered: 
group2 DivideAndConquer

10. If user wants to search by date, use the format yyyy-MM-dd. Fuzzy search is ok.

11. The project may be slow to load on first load as there are quite a few images. 

