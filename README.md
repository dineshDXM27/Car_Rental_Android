# car-rental-android

## Contents
- [Import Project](#import-project)
- [Setup Virtual Phone](#setup-virtual-phone)
- [Setup Sqlite Browser](#setup-sqlitebrowser)
- [Run Application](#run-application)
- [Add Users to Sqlite](#add-users-to-sqlite)
- [Change Users passwords to Sqlite](#change-system-user-passwords-to-sqlite)
- [Browse Users in Sqlite](#how-to-browse-sqlite-data)

## Setup
## Import Project
After cloning the git repo to your local computer, and opening Android 
Studio:
1. Select **Import Project**
![load](/images/load-menu.png)
1. Select the **build.gradle** file in the project under *car-rental-android* directory
and click **ok**.
![gradle](/images/build-gradle.png)
1. Should look as screen below.
![loaded](/images/loaded.png)

## Setup Virtual Phone
After loading the project in Android Studio:
1. Select the phone icon on the top right.
![phone-select](/images/phone-select.png)
1. Click the button **+ Create Virtual Device...** on the bottom
left to create a virtual phone to run the app on.
![phone-virt](/images/phone-virt.png)
1. Select **Pixel 2** option and click **Next**.
![pixel](/images/pixel.png)
1. Select the Release Name **R** and click **Download**, agree and 
download the image.
![r](/images/R.png)
1. Back on the System Image screen select the downloaded R image,
and click **Next**.
![r](/images/R.png)
1. Click **Finish**
![fin](/images/finish.png)

## Setup SqliteBrowser
1. Download and install sqlite browser from https://sqlitebrowser.org/dl/

## Run application
After loading the project in Android Studio, and 
creating a virtual phone:
1. On the top select **ApplicationMainScreen** (could be app) and 
select your virtual phone **Pixel 2 API 30**. Then click
the play button.
![play](/images/play.png)
1. Should see phone with clickable app buttons.
![phone](/images/app.png)


## Add users and sample data to Sqlite
After creating a virtual phone, installing Sqlite Browser, and running the application at
least once:
1. On the top select **ApplicationMainScreen** (could be app) and 
select your virtual phone **Pixel 2 API 30**. Then click
the play button.
![play](/images/play.png)
1. Open the **Device explorer** while the virtual phone is running
![manager](/images/device-manager.png)
1. In the **Device Explorer** Navigate down the path /data/data/org.uta.rental/databases
![databases](/images/databases.png)
1. Right click on **Users.db** and select **Save as**, use the default setting **TAKE NOTE OF THE 
SAVE LOCATION IN ANDROID STUDIO DIRECTORY** and click **Ok**.
1. Open sqlite browser
![sqlite-browser](/images/sqlbrowser.png)
1. Click the **Open Database** button
![sqlite-button](/images/opendb.png)
1. Navigate to where the **Users.db** sqlite file you exported from step 4, select **Users.db**
and open.
![openuser](/images/openuser.png)
1. Select the **Execute SQL** tab.
![tab](/images/exesql.png)
1. Paste the following below into the sql text box:
<pre>
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('7632446431449997424', '-2117454409', 'Sport', '0', '1', '0', '0', '2020-11-21T13:48:44.642', '2020-11-24T13:48:44.644', '1234', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('5794049876230268760', '1931493060', 'Sport', '0', '1', '0', '0', '2020-11-21T13:48:44.646', '2020-11-24T13:48:44.647', '1234', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('-5388477166748168499', '-1887331276', 'Sport', '2', '0', '0', '0', '2020-11-23T13:48:44.649', '2020-11-24T13:48:44.650', '1234', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('-5320169514147553650', '44903560', 'Sport', '3', '0', '0', '0', '2020-11-23T13:48:44.652', '2020-11-24T13:48:44.653', '1234', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('-2026810423496269269', '553020514', 'Sport', '0', '0', '0', '0', '2020-11-20T13:48:44.654', '2020-11-24T13:48:44.654', '1344', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('8873188197308195264', '-1655948228', 'Sport', '2', '0', '0', '0', '2020-11-23T13:48:44.655', '2020-11-24T13:48:44.656', '', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('9202782440243539233', '1049548057', 'Sport', '0', '0', '0', '0', '2020-11-23T13:48:44.658', '2020-11-24T13:48:44.659', '', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('-2046801816645851801', '-995273383', 'Sport', '1', '0', '0', '0', '2020-11-23T13:48:44.659', '2020-11-24T13:48:44.660', '', 'user');
INSERT INTO "main"."tbl_reservation" ("reservationnumber", "carnumber", "carname", "capacity", "gps", "onstar", "siriusxm", "startdatetime", "enddatetime", "aamemberid", "username") VALUES ('-4278400149759656163', '2067782975', 'Sport', '1', '0', '0', '0', '2020-11-20T13:48:44.662', '2020-11-24T13:48:44.663', '', 'user');
INSERT INTO "main"."tbl_registerUser" ("username", "password", "usertype", "utaid", "lastname", "firstname", "phone", "email", "streetaddress", "city", "state", "zipcode") VALUES ('user', 'password', 'user', '1234', 'doe', 'john', '2222222', 'foo@foo.com', 'foostreet', 'fooville', 'tx', '76244');
INSERT INTO "main"."tbl_registerUser" ("username", "password", "usertype", "utaid", "lastname", "firstname", "phone", "email", "streetaddress", "city", "state", "zipcode") VALUES ('admin', 'password', 'admin', '1234', 'doe', 'john', '2222222', 'foo@foo.com', 'foostreet', 'fooville', 'tx', '76244');
INSERT INTO "main"."tbl_registerUser" ("username", "password", "usertype", "utaid", "lastname", "firstname", "phone", "email", "streetaddress", "city", "state", "zipcode") VALUES ('manager', 'password', 'password', '1234', 'doe', 'john', '2222222', 'foo@foo.com', 'foostreet', 'fooville', 'tx', '76244');
</pre>
Should look like the following
![newuser](/images/new_users.png)
1. Click the play button to execute.
![exeplay](/images/exeplay.png)
1. Write the database changes by clicking the **Write Changes** Button
![write](/images/writedb.png)
1. Go back to **Device Explorer** in Android Studio
![databases](/images/databases.png)
1. Right click on the **databases** folder and select **Upload**
![uploaddb](/images/uploaddb.png)
1. Navigate to where the **Users.db** sqlite file you exported from step 4, select **Users.db** and
click **Ok**.
![uploaduser](/images/uploaduser.png)

## Change System user passwords to Sqlite
After creating a virtual phone, installing Sqlite Browser, and adding users to sqlite database:
1. On the top select **ApplicationMainScreen** (could be app) and 
select your virtual phone **Pixel 2 API 30**. Then click
the play button.
![play](/images/play.png)
1. Open the **Device explorer** while the virtual phone is running
![manager](/images/device-manager.png)
1. In the **Device Explorer** Navigate down the path /data/data/org.uta.rental/databases
![databases](/images/databases.png)
1. Right click on **Users.db** and select **Save as**, use the default setting **TAKE NOTE OF THE 
SAVE LOCATION IN ANDROID STUDIO DIRECTORY** and click **Ok**.
1. Open sqlite browser
![sqlite-browser](/images/sqlbrowser.png)
1. Click the **Open Database** button
![sqlite-button](/images/opendb.png) 1. Navigate to where the **Users.db** sqlite file you exported from step 4, select **Users.db**
and open.
![openuser](/images/openuser.png)
1. Select the **Execute SQL** tab.
![tab](/images/exesql.png)
1. Paste the following below into the sql text box:
<pre>
update `tbl_registerUser`
set password='pass'
where username='user';

update `tbl_registerUser`
set password='pass'
where username='manager';

update `tbl_registerUser`
set password='pass'
where username='admin';
</pre>
Should look like the following
![newuser](/images/new_users.png)
1. Click the play button to execute.
![exeplay](/images/exeplay.png)
1. Write the database changes by clicking the **Write Changes** Button
![write](/images/writedb.png)
1. Go back to **Device Explorer** in Android Studio
![databases](/images/databases.png)
1. Right click on the **databases** folder and select **Upload**
![uploaddb](/images/uploaddb.png)
1. Navigate to where the **Users.db** sqlite file you exported from step 4, select **Users.db** and
click **Ok**.
![uploaduser](/images/uploaduser.png)

## How to Browse Sqlite data
After creating a virtual phone, installing Sqlite Browser, and adding users to sqlite database:
1. On the top select **ApplicationMainScreen** (could be app) and 
select your virtual phone **Pixel 2 API 30**. Then click
the play button.
![play](/images/play.png)
1. Open the **Device explorer** while the virtual phone is running
![manager](/images/device-manager.png)
1. In the **Device Explorer** Navigate down the path /data/data/org.uta.rental/databases
![databases](/images/databases.png)
1. Right click on **Users.db** and select **Save as**, use the default setting **TAKE NOTE OF THE 
SAVE LOCATION IN ANDROID STUDIO DIRECTORY** and click **Ok**.
1. Open sqlite browser
![sqlite-browser](/images/sqlbrowser.png)
1. Click the **Open Database** button
![sqlite-button](/images/opendb.png)
1. Navigate to where the **Users.db** sqlite file you exported from step 4, select **Users.db**
and open.
![openuser](/images/openuser.png)
1. In the **Database Structure** tab right click the table **tbl_registerUser** and select
**Browse Table**
![data](/images/data.png)
