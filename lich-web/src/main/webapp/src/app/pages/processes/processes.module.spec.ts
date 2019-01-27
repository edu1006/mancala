import { ProcessesModule } from './processes.module';

describe('AdministrationModule', () => {
  let processesModule: ProcessesModule;

  beforeEach(() => {
    processesModule = new ProcessesModule();
  });

  it('should create an instance', () => {
    expect(processesModule).toBeTruthy();
  });
});
