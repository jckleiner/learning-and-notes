
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
 * Limit Login Attempts Reloaded
 * WPS Hide Login
 * Disable Search

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
UpdraftPlus WordPress Backup Plugin
 * Settings -> `Files backup schedule` and `Database backup schedule`
 * Retain this many scheduled backups: 4, meaning that it will always keep only the last 4 backups, the rest will be deleted
 * You can also setup a cloud provider (Nexcloud NOT supported). Your backup files will be automatically uploaded there after the auth process.
   This will create a folder called `UpdraftPlus` (can't change name in free version).
   TODO: Will it also delete the older backups from the cloud folder?

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
 * WP Mail SMTP Plugin and you need to give an SMTP server -> Either Google or a custom domain provider like Strato (When you buy a domain name at Strato, you also get the email domain)
 * WP Forms -> you can give more than one email comma separated.
 * TODO: Sometimes Emails won't arrive? An automatic check regularly???

### WP security best practices
 * Don't have a user called 'admin'
 * Limit Login Attempts Reloaded: Settings >> GDPR compliance check
 * WPS Hide Login: changes the default login URLs to something custom
 * (Plugin) Disable Search: Disables search bar on a 404 page, from which different things can be accessed (posts etc.) which we sometimes don't want
 * Create a custom privacy policy page. The default one still has the default template links to login, posts search etc.
 * Delete inactive users, unused plugins and themes.
 * Do regular updates.

### Optimization
 * https://developers.google.com/speed/pagespeed/insights
 * https://gtmetrix.com/
 * https://web.dev/measure/

 1. Optimize Images: ShortPixel (give unused email) (or Optimole), lossy, add webp versions
 2. Cache: WP Fastest Cache
    * Settings -> Permalink -> choose any other than the default
    * Plugin Settings -> select all -> save and then clear cache

### Google SEO
  * Google Search Console registration
  * Google Local Business registration. This is very similar to the local business schema that you can integrate to your website. Having both is not bad but it should be enough to just have a "Google Local Business" registration for a small website.

### Custom privacy page impressum, datenschutz etc.
TODO
 * custom

### Migrate prod site (with HTTPS enabled) to localhost for development/testing
  1. delete the local `html` and `database_data` folders
  2. `.env` file with random credentials. These will be overridden after importing the backup locally
  3. `docker-compose up -d` and setup wp
  4. Install plugin `All-in-One WP Migration`
  5. Import -> select import file (make sure you have enough upload limit locally - the import might be more than 250 MB)
  6. This plugin also changes the URL to `localhost:8000` correctly, which means we don't need to fix the URLs or convert https to http manually by changing database entries.

### SEO / meta tags and stuff
TODO
 * Registered at Google Search Console, pasted the snippet it gave to YOAST SEO to prove ownership

### Certificate with a plugin
TODO
 * Plugin: Really Simple SSL, configures everything, you only need to provide a certificate
 * What does it do exactly? Nginx config? How to use it with lets encrypt certs?

### Multi language support?
TODO
 * DE/EN

### Docker Setup
TODO
 * Docker compose looks for a .env file in the current directory for the environment variables. 

### Freenom: Free Domain Name

 * Change the A record to point to your servers ip. One with no name. The other has "www" as name

## TODOs

 * Visitor statistics
 * Stratum 20 widgets, happy addons
 * Starter Themes - braninstorm force
 * Video Background
 * Free Sticky Header
 * fix background image for mobile and tablet
 * shape divider
 * fade in effects?
 * Plugin: Smash Balloon Instagram Feed, user needs to login to insta and then connect, how long will it last? Not sure
 * Auto updates wordpress disable or is it safe?