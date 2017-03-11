package services

import javax.inject.Inject

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase
import play.api.cache
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer


/**
  * Created by knoldus on 7/3/17.
  */
class CacheService @Inject()(cache: CacheApi) extends CacheTrait {

  def setCache(value: String, newObject: UserDetails) :Boolean= {
    val list = cache.get[List[UserDetails]](value).toList.flatten
    cache.set(value, list :+ newObject)
    true
  }

  def getCache(value: String) = {
    cache.get[List[UserDetails]](value)
  }

}