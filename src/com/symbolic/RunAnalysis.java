package com.symbolic;

import soot.*;
import soot.options.Options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mengyuan.ymy on 2016/11/11.
 */
public class RunAnalysis {

    public static void main(String[] args) {


        Options.v().set_soot_classpath("/Users/haha/code/tmp/tutorial1/target/classes");
        Options.v().set_jardir_classpath("/Users/haha/code/tmp/tutorial1/target/wardir/WEB-INF/lib");
        //Options.v().set_pass_classname_regex("(javax?|jdk|sun)\\.");
        Options.v().set_prepend_classpath(true);
        Options.v().set_ignore_resolution_errors(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_whole_program(true);


        List<String> argsList = new ArrayList<String>(Arrays.asList(args));
        argsList.addAll(Arrays.asList(new String[]{
                //"-p", "cg.cha", "off",
                "-p", "jb", "use-original-names:true",
                "-w",
                "-p", "cg", "enabled:false",
                "-main-class",
                "com.test.HessianPoC",//main-class
                "com.test.HessianPoC"//argument classes
        }));


        PackManager.v().getPack("wjap").add(new Transform("wjap.myTrans", new SceneTransformer() {



            @Override
            protected void internalTransform(String phaseName, Map options) {
                //CHATransformer.v().transform();

                VM vm = new VM();
                SootMethod method = Scene.v().getMainClass().getMethodByName("main");

                List<ValueProperty> params = new ArrayList<ValueProperty>();
                ValueProperty param = new ValueProperty();
                params.add(param);

                vm.visitMethod(method, null, params);
            }

        }));

        args = argsList.toArray(new String[0]);

        soot.Main.main(args);
    }
}
