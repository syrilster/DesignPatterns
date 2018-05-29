**To Mock a DB call kind of method using thenAnswer**

```
when(someBeanMock.someClass.findAllByFilter(any(ClinicalItemFilter.class))).thenAnswer(
                new Answer<List<ClinicalItem>>() {
                    @Override
                    public List<ClinicalItem> answer(InvocationOnMock invocationOnMock) {
                        ClinicalItemFilter clinicalItemFilter = (ClinicalItemFilter) invocationOnMock.getArguments()[0];
                        if (clinicalItemFilter.getClinicalItemTypeKeys().containsAll(clinicalItemTypeKeys)) {
                            return clinicalItemsWhenPatientAlertEnabled;
                        } else if (clinicalItemFilter.getClinicalItemTypeKey().equals(CLINICAL_ITEM_ADVANCE_DIRECTIVE_TYPE)) {
                            return clinicalItemWhenPatientAlertDisabled;
                        }
                        return new ArrayList<>();
                    }
                });
```
