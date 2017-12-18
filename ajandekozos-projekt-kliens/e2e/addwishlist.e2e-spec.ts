import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Create New Wishlist functionality', () => {
    beforeEach(() => {
        browser.manage().deleteAllCookies();
        browser.get('/login');
        element(by.buttonText('Login')).click();
        element(by.css('input[type="text"]')).sendKeys('kosa');
        element(by.css('input[type="password"]')).sendKeys('kosa');
        element(by.css('input[name="bejelentkezes"]')).click();
    });
      
    it('should navigate to the my wishlists page', () => {
        element(by.css('input[name="lists"]')).click();
        expect(getPath()).toEqual('/user/wishlists');
    });

    it('should fail to create wishlist for empty text', () => {
        browser.get('/user/wishlists');
        element(by.css('input[name="createlist"]')).click();
        var error = element(by.css('input[name="error"]'));
        expect(error.isPresent()).toBeTruthy();
    });

    it('should create wishlist in case of a text input', () => {
        browser.get('/user/wishlists');
        element(by.css('input[name="title"]')).sendKeys('éasklkélaskéskdj');
        element(by.css('input[name="createlist"]')).click();
        var error = element(by.css('input[name="error"]'));
        expect(error.isPresent()).toBeFalsy();
    });

});