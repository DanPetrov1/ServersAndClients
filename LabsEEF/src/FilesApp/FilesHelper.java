package FilesApp;


/**
* FilesApp/FilesHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from App.idl
* 7 �������� 2019 �. 11:47:44 GMT+06:00
*/

abstract public class FilesHelper
{
  private static String  _id = "IDL:FilesApp/Files:1.0";

  public static void insert (org.omg.CORBA.Any a, FilesApp.Files that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static FilesApp.Files extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (FilesApp.FilesHelper.id (), "Files");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static FilesApp.Files read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_FilesStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, FilesApp.Files value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static FilesApp.Files narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof FilesApp.Files)
      return (FilesApp.Files)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      FilesApp._FilesStub stub = new FilesApp._FilesStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static FilesApp.Files unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof FilesApp.Files)
      return (FilesApp.Files)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      FilesApp._FilesStub stub = new FilesApp._FilesStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
