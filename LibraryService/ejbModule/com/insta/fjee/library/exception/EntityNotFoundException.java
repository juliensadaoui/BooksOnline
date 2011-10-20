package com.insta.fjee.library.exception;

public class EntityNotFoundException extends Exception 
{
		private static final long serialVersionUID = 1L;

		private String className;
		private Integer id;

		public EntityNotFoundException(Class<?> clazz, Integer id) {
			super("Entity of class " + clazz.getSimpleName() + " with id " + id + " not found");
			this.className = clazz.getSimpleName();
			this.id = id;
		}

		public String getClassName()
		{
			return className;
		}

		public Integer getId()
		{
			return id;
		}
	}