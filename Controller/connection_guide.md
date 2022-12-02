## steps

1. go Mysql client cmd
2. type in the following commands

   - `create user 'temp_user'@'localhost' IDENTIFIED BY 'mypassword'`
   - `GRANT ALL PRIVILEGES ON * . * TO 'temp_user'@'localhost';`
   - `FLUSH PRIVILEGES;`

3. check the external library is imported
   <img src></src>
4. log in your local MySQL, type in the following in cmd

   ```cmd
   mysql -u temp_user -p

   ```

   then enter 'mypassword' as password

5. once log in, run user.sql file to import necessary database and table
6. 
