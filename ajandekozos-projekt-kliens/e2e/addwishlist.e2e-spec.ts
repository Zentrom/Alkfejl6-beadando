import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Create New Wishlist functionality', () => {
    beforeAll(() => {
        browser.manage().deleteAllCookies();
        browser.get('');
        element(by.buttonText('Login')).click();
        element(by.css('input[type="text"]')).sendKeys('kosa');
        element(by.css('input[type="password"]')).sendKeys('kosa');
        element(by.id('bejelentkezes')).click();
    });

    beforeEach(() => {
        element(by.id('lists')).click();
      });
      
    it('should navigate to the my wishlists page', () => {    
        expect(getPath()).toEqual('/user/wishlists');
    });

    it('should fail to create wishlist for empty text', () => {
        element(by.buttonText('Create list')).click();
        expect(element.all(by.id("sor")).count()).toEqual(1);
        var error = element(by.id('error'));
        expect(error.isPresent()).toBeTruthy();
    });

    it('should create wishlist in case of a text input', () => {
        element(by.css('input[type="text"]')).sendKeys('éasklkélaskéskdj');
        element(by.buttonText('Create list')).click();
        var error = element(by.id('error'));
        expect(error.isPresent()).toBeFalsy();
    });

});