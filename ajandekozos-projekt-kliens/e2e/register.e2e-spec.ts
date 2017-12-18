import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Register functionality', () => {
    beforeEach(() => {
        element(by.buttonText('Register')).click();
    });
  
  
    it('should register a new user and navigate to login', () => {
      element(by.css('input[name="username"]')).sendKeys('xyz123');
      element(by.css('input[name="password"]')).sendKeys('xyz123');
      element(by.css('input[name="firstname"]')).sendKeys('123xyz');
      element(by.css('input[name="lastname"]')).sendKeys('x1y2z3');
      element(by.css('input[name="email"]')).sendKeys('xyz@gmai.com');
      element(by.buttonText('Register')).click();
      expect(getPath()).toEqual('');
    });
  
  });