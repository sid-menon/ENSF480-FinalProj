## warning:

- if a database called theatre_app located in local device, it is recomemded to change the database name at the first three lines of pure_entity_table.sql

## steps

1. go Mysql client cmd
2. type in the following commands

   - `create user 'temp_user'@'localhost' IDENTIFIED BY 'mypassword'`
   - `GRANT ALL PRIVILEGES ON * . * TO 'temp_user'@'localhost';`
   - `FLUSH PRIVILEGES;`

3. check the external library is imported
   <img src></src>
4. log in your local MySQL, type in the following in the IDE terminal

   ```cmd
   mysql -u temp_user -p

   ```

   then enter 'mypassword' as password

5. once log in, run user.sql file to import necessary database and table
6. import the database tables by typing the following:

   - `source pure_entity_table.sql`
   - `source foreign_entity_table.sql`

   <strong>Note: </strong>
   the database come with an preset admin user

   email: fbcharles747@gmail.com

   password: mypassword

7. install JDBC driver
8. add the driver to IDE library dependency
