import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Handling Friend-requests functionality', () => {
    beforeAll(() => {
        browser.manage().deleteAllCookies();
        browser.get('');
        element(by.buttonText('Login')).click();
        element(by.id('us')).sendKeys('negrut');
        element(by.id('pw')).sendKeys('gyere');
        element(by.id('bejelentkezes')).click();        
    });

    beforeEach(() => {
        element(by.id('requests')).click();
      });

    it('should navigate to the incoming requests page', () => { 
        expect(getPath()).toEqual('/user/friendrequests');
    });

    it('should show all the incoming requests', () => {
        element(by.buttonText('Show all')).click();
        let list = element.all(by.css('tr'));
        expect(list.count()).toBe(1);
    });

    it('should eliminate a request in case of a decline', () => {
        element(by.buttonText('Show all')).click();
        element(by.id('decline')).click();
        expect(element.all(by.id("sor")).count()).toEqual(0);
    });

    it('should eliminate a request in case of an accept', () => {
        element(by.buttonText('Show all')).click();
        element(by.id('accept')).click();
        expect(element.all(by.id("sor")).count()).toEqual(0);
    });

});