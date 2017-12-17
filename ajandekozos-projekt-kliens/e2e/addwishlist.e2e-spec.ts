import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Create New Wishlist functionality', () => {
    beforeEach(() => {
        browser.get('/');
        element(by.buttonText('Login')).click();
        element(by.css('input[type="text"]')).sendKeys('kosa');
        element(by.css('input[type="password"]')).sendKeys('kosa');
        element(by.id('bejelentkezes')).click();
        element(by.buttonText('My Wishlists')).click();
    });

    it('should navigate to the my wishlists page', () => {    
        
        expect(getPath()).toEqual('/user/wishlists');
    });

    it('should fail to create wishlist for empty text', () => {
        //element(by.buttonText('My Wishlists')).click();
        element(by.buttonText('Create list')).click();
        expect(element.all(by.id("sor")).count()).toEqual(1);
    });

    it('should create wishlist in case of a text input', () => {
        //element(by.buttonText('My Wishlists')).click();
        element(by.css('input[type="text"]')).sendKeys('éasklkélaskéskdj');
        element(by.buttonText('Create list')).click();
        expect(element.all(by.id("sor")).count()).toEqual(2);
    });

});