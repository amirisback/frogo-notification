# Frogo Notification (work-in-progress 👷🔧️👷‍♀️⛏)
Beta Release

# About This Project
SDK for anything your problem to make easier developing android apps
frogo-notification is under huge large development

# Version Release
This Is Latest Release

    $version_release = 1.0.0

What's New??

    * Notification with singleton method *
    * Simple and eazy to use *

# Download this project

### Step 1. Add the JitPack repository to your build file (build.gradle : Project)

    Add it in your root build.gradle at the end of repositories:

    	allprojects {
    		repositories {
    			...
    			maven { url 'https://jitpack.io' }
    		}
    	}

### Step 2. Add the dependency (build.gradle : Module)

    dependencies {
            // library frogo-notification
            implementation 'com.github.amirisback:frogo-notification:1.0.0'
    }

### Step 3. Implement frogo-notification

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/amirisback"))
    val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

    val frogoNotification = FrogoNotification.Inject(this)
        .setNotificationId(NOTIFICATION_ID)
        .setChannelId(CHANNEL_ID)
        .setChannelName(CHANNEL_NAME)
        .setResoures(resources)
        .setContentIntent(pendingIntent)
        .setSmallIcon(R.drawable.ic_frogo_notif)
        .setLargeIcon(R.drawable.ic_frogo_notif)
        .setContentTitle(resources.getString(R.string.content_title))
        .setContentText(resources.getString(R.string.content_text))
        .setSubText(resources.getString(R.string.subtext))
        .setAutoCancel(true)
        .build()

    frogoNotification.launch() // notify (show the notification)

# Colaborator
Very open to anyone, I'll write your name under this, please contribute by sending an email to me

- Mail To faisalamircs@gmail.com
- Subject : Github _ [Github-Username-Account] _ [Language] _ [Repository-Name]
- Example : Github_amirisback_kotlin_admob-helper-implementation

Name Of Contribute
- Muhammad Faisal Amir
- Waiting List
- Waiting List

Waiting for your contribute

# Attention !!!
- Please enjoy and don't forget fork and give a star
- Don't Forget Follow My Github Account