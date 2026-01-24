package dev.kirro.extendedcombat.api;

import com.mojang.authlib.EnvironmentParser;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforgespi.Environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface CheckEnvironment {
    Dist value();
}
