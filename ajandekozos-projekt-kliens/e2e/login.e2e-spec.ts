import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Login functionality', () => {
    beforeEach(() => {
        browser.get('');
    });

    it('should navigate to the login page', () => {    
        element(by.buttonText('Login')).click();
        expect(getPath()).toEqual('/login');
    });

    it('should fail to login for empty credentials', () => {
        element(by.buttonText('Login')).click();
        element(by.id('bejelentkezes')).click();
        expect(getPath()).toEqual('/login');
    });

    it('should fail to login for invalid credentials', () => {
        element(by.css('input[type="text"]')).sendKeys('éasklduélasukdtzél@léasdkélasku.aléskdj');
        element(by.css('input[type="password"]')).sendKeys('éasklduélasukdtzé');
        element(by.id('bejelentkezes')).click();
        expect(getPath()).toEqual('/login');
    });

    it('should log in for valid credentials', () => {
        element(by.css('input[type="text"]')).sendKeys('negrut');
        element(by.css('input[type="password"]')).sendKeys('gyere');
        element(by.id('bejelentkezes')).click();
        expect(getPath()).toEqual('/');
    });
});