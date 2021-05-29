

## Tutorials
 * https://www.youtube.com/watch?v=2vKf3V0AL8I&t=837s

## Plugins
 * Elementor Website Builder
 * Essential Addons for Elementor
 * WP Forms
 * All in one WP migration
 * UpdraftPlus WordPress Backup Plugin
 * White Label CMS: for custom login page and other dashboard customizations
 * Smash Balloon Social Photo Feed 

## WP redirects localhost to https localhost OR siteurl/wp-admin does not load css
This happened because I changed 'siteUrl', 'home', 'site title' and 'tagline' in the WP settings page.
To fix this: 
 * Go to wp database -> wp_options table -> change 'siteurl', 'home', 'blogname' and 'blogdescription' back to old values

## Change PHP Settings (for example increase upload file size)
Custom .ini files can be added to `/usr/local/etc/php/conf.d/` to override the default configuration in `php.ini`

    volumes:
      - ./custom-settings.ini:/usr/local/etc/php/conf.d/custom-settings.ini

## Migrate Site
This process also migrates the DB users and all other (Media, etc.) settings:
 * Install the Plugin `All in one WP migration` on the server which needs to be migrated
 * Migration Plugin -> Export -> Export to file
 * Setup an empty Wordpress site on the target server and install the `All in one WP migration`
   The credentials are not important because they will be overridden.
 * Migration Plugin -> Import -> Import from file

## Regular Backups
 * UpdraftPlus WordPress Backup Plugin -> Settings -> `Files backup schedule` and `Database backup schedule`
 * ... and retain this many scheduled backups: 4, meaning that it will always keep only the last 4 backups, the rest will be deleted
 * You can also setup a cloud provider. Your backup files will be automatically uploaded there

## Optimization
 * https://developers.google.com/speed/pagespeed/insights
 * https://gtmetrix.com/

 1. Optimize Images: ShortPixel (give unused email) (or Optimole), lossy, add webp versions
 2. Hummingbird:
    * Gzip code for Ngingx copy paste
 2. Cache: WP Fastest Cache
    * Settings -> Permalink -> choose any other than the default
    * Plugin Settings -> select all -> save and then clear cache
 3. 

## TODOs

 * Stratum 20 widgets, happy addons
 * Starter Themes - braninstorm force
 * Video Background
 * Security best practices?
 * fix background image for mobile and tablet
 * footer widgets, different sections for each text and also links, privacy etc.
 * shape divider
 * fade in effects?
 * image compression - resmush.it image optimizer
 * Plugin: Smash Balloon Instagram Feed, Alex needs to login to insta and then connect, how long will it last? Not sure
 * Page template "elementor" is not selected. Will that be a problem?
 * Adjust padding: "widget space"
 * Dockerfile passwords as env variables?
 * apache inside the container and another apache outside?
 * Test if backups work or not
 * image url's start with localhost:8000, check after migration if everything is fine.
 * meta tags / SEO?
 * "Firefox Can’t Open This Page" Google Maps
 * Auto updates wordpress disable or is it safe?
 * Don't allow db access from outside
 * Mobile Navigation
 * Your trainer, images only in desktop
 * Copyright  © 2021
 * Astra theme reference is a must?
 * Email sending process
 * impressum, datenschutz etc.
 * Jenkins for health check -> main page, check if 200
 * Whatsapp icon in contact?
 * Bjj akademie in header picture?
 * DE/EN