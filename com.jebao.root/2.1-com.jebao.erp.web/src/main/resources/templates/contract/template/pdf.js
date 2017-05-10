var system = require('system');
var address = system.args[1];
var output = system.args[2];
var page = require('webpage').create();
page.paperSize = {
    format: 'A4',
    orientation: 'portrait',
    border: '1cm'
};
// debug begin
//console.log(output);
//console.log(address);
//console.log(status);
// debug end
page.settings.resourceTimeout = 14000;
page.open(address, function (status) {
    if (status !== 'success') {
        console.log('[ERROR]');
        phantom.exit();
    }});
page.onLoadFinished = function (status) {
    setTimeout(function () {
        page.render(output);
        //console.log('Status: ' + status);
        console.log("[OK]");
        phantom.exit();
    }, 0);
};