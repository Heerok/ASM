var gulp = require('gulp');
var concat = require('gulp-concat');
var debug = require('gulp-debug');

gulp.task('default', function() {
    return gulp.src(
        [
            './lib/angularAll/*.js',
            './app/*.js',        //this will only pick up files under app, not recursive!
            './app/service/auth.service.js',
            './js/notify.min.js'
        ])
        .pipe(debug({title: 'PROCESSING:'}))
        .pipe(concat('all.js'))
        .pipe(gulp.dest('dist'));
});