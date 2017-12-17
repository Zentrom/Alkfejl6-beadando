import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Handling Friend-requests functionality', () => {
    beforeAll(() => {
        browser.get('');
        element(by.css('input[type="text"]')).sendKeys('negrut');
        element(by.css('input[type="password"]')).sendKeys('gyere');
        element(by.id('bejelentkezes')).click();
    });

    it('should navigate to the incoming requests page', () => {    
        element(by.buttonText('Incoming requests')).click();
        expect(getPath()).toEqual('/user/friendrequests');
    });

    it('should show all the incoming requests', () => {
        element(by.buttonText('Incoming requests')).click();
        element(by.buttonText('Show all')).click();
        expect(element.all(by.id("sor")).count()).toEqual(1);
    });

    it('should eliminate a request in case of a decline', () => {
        element(by.buttonText('Incoming requests')).click();
        element(by.buttonText('Show all')).click();
        element(by.buttonText('Decline')).click();
        expect(element.all(by.id("sor")).count()).toEqual(0);
    });

    it('should eliminate a request in case of an accept', () => {
        element(by.buttonText('Incoming requests')).click();
        element(by.buttonText('Show all')).click();
        element(by.buttonText('Accept')).click();
        expect(element.all(by.id("sor")).count()).toEqual(0);
    });

});