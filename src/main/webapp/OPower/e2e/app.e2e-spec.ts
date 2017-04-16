import { OPowerPage } from './app.po';

describe('opower App', () => {
  let page: OPowerPage;

  beforeEach(() => {
    page = new OPowerPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
