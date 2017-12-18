import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Handling Friend-requests functionality', () => {
    beforeEach(() => {
        browser.manage().deleteAllCookies();
        browser.get('/login');
        element(by.css('input[type="text"]')).sendKeys('negrut');
        element(by.css('input[type="password"]')).sendKeys('gyere');
        element(by.id('bejelentkezes')).click();      
    });

    it('should navigate to the incoming requests page', () => { 
        element(by.id('requests')).click();
        expect(getPath()).toEqual('/user/friendrequests');
    });

    it('should show all the incoming requests', () => {
        browser.get('/user/friendrequests');
        element(by.id('showall')).click();
        let list = element.all(by.css('tr'));
        expect(list.count()).toBe(1);
    });

    it('should eliminate a request in case of a decline', () => {
        browser.get('/user/friendrequests');
        element(by.id('showall')).click();
        element(by.id('decline')).click();
        expect(element.all(by.id("sor")).count()).toEqual(0);
    });

    it('should eliminate a request in case of an accept', () => {
        browser.get('/user/friendrequests');
        element(by.id('showall')).click();
        element(by.id('accept')).click();
        expect(element.all(by.id("sor")).count()).toEqual(0);
    });

});