import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Handling Friend-requests functionality', () => {
    beforeEach(() => {
        browser.get('');
        element(by.buttonText('Login')).click();
        element(by.id('us')).sendKeys('negrut');
        element(by.id('pw')).sendKeys('gyere');
        element(by.id('bejelentkezes')).click();
        
    });
  // browser.manage().timeouts().pageLoadTimeout(40000);
  // browser.manage().timeouts().implicitlyWait(25000);

    it('should navigate to the incoming requests page', () => { 
        
        getPath();
        element(by.id('inc')).click();
        //element(by.buttonText('Incoming requests')).click();
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