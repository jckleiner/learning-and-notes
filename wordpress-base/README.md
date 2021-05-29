
## Wordpress Stuff
### Tutorials
 * https://www.youtube.com/watch?v=2vKf3V0AL8I&t=837s

### Plugins
 * Elementor Website Builder
 * Essential Addons for Elementor
 * WP Forms
 * All in one WP migration
 * UpdraftPlus WordPress Backup Plugin
 * White Label CMS: for custom login page and other dashboard customizations
 * Smash Balloon Social Photo Feed
 * WP Mail SMTP Plugin
 * WP Fastest Cache
 * ShortPixel: Image compression

### WP redirects localhost to https localhost OR siteurl/wp-admin does not load css
This happened because I changed 'siteUrl', 'home', 'site title' and 'tagline' in the WP settings page.
To fix this: 
 * Go to wp database -> wp_options table -> change 'siteurl', 'home', 'blogname' and 'blogdescription' back to old values

### Change PHP Settings (for example increase upload file size)
Custom .ini files can be added to `/usr/local/etc/php/conf.d/` to override the default configuration in `php.ini`

    volumes:
      - ./custom-settings.ini:/usr/local/etc/php/conf.d/custom-settings.ini

### Migrate Site
This process also migrates the DB users and all other (Media, etc.) settings:
 * Install the Plugin `All in one WP migration` on the server which needs to be migrated
 * Migration Plugin -> Export -> Export to file
 * Setup an empty Wordpress site on the target server and install the `All in one WP migration`
   The credentials are not important because they will be overridden.
 * Migration Plugin -> Import -> Import from file

### Regular Backups
 * UpdraftPlus WordPress Backup Plugin -> Settings -> `Files backup schedule` and `Database backup schedule`
 * ... and retain this many scheduled backups: 4, meaning that it will always keep only the last 4 backups, the rest will be deleted
 * You can also setup a cloud provider. Your backup files will be automatically uploaded there

### Switching to HTTPS

 * WP Settings >> change title and site to https

 * If error "you site does not redirect properly" infinite redirects: `sudo nano wp-config.php`

        define('FORCE_SSL_ADMIN', true);
        
        // in some setups HTTP_X_FORWARDED_PROTO might contain, a comma-separated list e.g. http,https
        // so check for https existence. Make sure this is !== true and NOT false
        if (strpos($_SERVER['HTTP_X_FORWARDED_PROTO'], 'https') !== true)
            $_SERVER['HTTPS']='on';

        define('WP_HOME','https://<YOUR_DOMAIN>.de');
        define('WP_SITEURL','https://<YOUR_DOMAIN>.de');

 * Some images might still have the http url. Clear cache

### E-Mail Server
 * WP Mail SMTP Plugin and you need to give an SMTP server

### Optimization
 * https://developers.google.com/speed/pagespeed/insights
 * https://gtmetrix.com/

 1. Optimize Images: ShortPixel (give unused email) (or Optimole), lossy, add webp versions
 2. Cache: WP Fastest Cache
    * Settings -> Permalink -> choose any other than the default
    * Plugin Settings -> select all -> save and then clear cache

### Privacy page impressum, datenschutz etc.
TODO

### Migrate prod site (with HTTPS) to localhost
TODO

### WP security best practices
TODO

### SEO / meta tags and stuff
TODO
## TODOs

 * Visitor statistics
 * Stratum 20 widgets, happy addons
 * Starter Themes - braninstorm force
 * Video Background
 * fix background image for mobile and tablet
 * shape divider
 * fade in effects?
 * Plugin: Smash Balloon Instagram Feed, user needs to login to insta and then connect, how long will it last? Not sure
 * Auto updates wordpress disable or is it safe?
 * DE/EN