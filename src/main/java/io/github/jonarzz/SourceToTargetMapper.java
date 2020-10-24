package io.github.jonarzz;

class SourceToTargetMapper {

    private TargetModelObjectFactory targetModelObjectFactory;

    SourceToTargetMapper(TargetModelObjectFactory targetModelObjectFactory) {
        this.targetModelObjectFactory = targetModelObjectFactory;
    }

    public Target apply(Source source) {
        try {
            Target target = targetModelObjectFactory.create(Target.class);
            // other methods
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
