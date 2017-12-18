// conf.js
exports.config = {
    framework: 'jasmine',
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['login.e2e-spec.ts','addwishlist.e2e-spec.ts','handlingrequest.e2e-spec'],
  }
  