# car-rental-android

# Setup
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


## Add users to Sqlite
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
INSERT INTO `tbl_registerUser`(`username`,`password`,`usertype`) VALUES ("user", "password", "user");
INSERT INTO `tbl_registerUser`(`username`,`password`,`usertype`) VALUES ("admin", "password", "admin");
INSERT INTO `tbl_registerUser`(`username`,`password`,`usertype`) VALUES ("manager", "password", "rental_manager");
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
